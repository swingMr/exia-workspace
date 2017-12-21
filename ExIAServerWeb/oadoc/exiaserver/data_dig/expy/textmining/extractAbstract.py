#!/usr/bin/env python
# encoding: utf-8

import sys
import os
import json
import jieba
import jieba.analyse
import networkx as nx
from datetime import datetime
from sklearn.feature_extraction.text import TfidfVectorizer, TfidfTransformer

def loadStopwords(path):
    with open(path, 'rb') as f:  
        stopwords = list(filter(lambda x: x, map(lambda x: x.strip().decode('utf-8', 'ignore'), f.readlines())))  
    stopwords.extend([' ', '\t', '\n'])
    return frozenset(stopwords)

def loadSubjectTerms(path):
    with open(path, 'rb') as f:
        subjectTerms = list(filter(lambda x: x, map(lambda x: x.strip().decode('utf-8', 'ignore'), f.readlines())))  
    return frozenset(subjectTerms)

def extract(title, text, stopWords=None, subjectTerms=None, limit=20):
    if text==None : return None
    
    keywords = []
    titleTags = []
    if title!=None and len(title)>0 :
        titleTags = jieba.analyse.extract_tags(title)
        for titleTag in titleTags:
            keywords.append([titleTag,1])
    
    textTags = jieba.analyse.extract_tags(text,topK=limit, withWeight=True)
    for tag in textTags :
        if stopWords!= None and stopWords.__contains__(tag[0]):
            continue
        if subjectTerms!=None and subjectTerms.__contains__(tag[0]) and tag[0] not in titleTags:
            keywords.append([tag[0], tag[1] if len(titleTags)>0 and titleTags.count(tag[0])==0 else tag[1]+0.5])
    
    keywords.sort(key=lambda x : x[1], reverse=True)
    
    return [{"word": keyword[0], "relevantLevel": keyword[1]} for keyword in keywords]

def cutSentence(sentence):
    if not isinstance(sentence, str):  
        sentence = sentence.decode('utf-8')
    delimiters = frozenset(u'。！？')
    buf = []  
    for ch in sentence:  
        buf.append(ch)  
        if delimiters.__contains__(ch):  
            yield ''.join(buf)  
            buf = []  
    if buf:  
        yield ''.join(buf)

def abstract(title=None, text=None, stopWords=None, length=250):
    if text == None : 
        return None
    
    sentences = list(cutSentence(text))
    words = None
    if title != None :
        words = jieba.cut(title)
    
    tokenizer = jieba.cut
    if words != None :
        words = list(words)
        tokenizer = (lambda sentence : filter(lambda x : words.__contains__(x), jieba.cut(sentence)));
    try:
        tfidfModel = TfidfVectorizer(tokenizer=tokenizer, stop_words=stopWords)
        tfidfMatrix = tfidfModel.fit_transform(sentences)
        normalizedMatrix = TfidfTransformer().fit_transform(tfidfMatrix)
        similarity = nx.from_scipy_sparse_matrix(normalizedMatrix * normalizedMatrix.T)
        scores = nx.pagerank(similarity)
        tops = sorted(scores.items(), key=lambda x: x[1], reverse=True)
        size = min(3, len(sentences))
        indices = [ top[0] for top in tops[:size]]
        return ";".join(list(map(lambda idx: sentences[idx], indices)))[:length]
    except:
        return "根据输入标题无法获取正文摘要"
if __name__ == '__main__':
    title = None
    text = None
    keyWordLimit = 20
    abstractLength = 250
    
    if len(sys.argv)==1 :
        sys.exit()
    
    if len(sys.argv) == 2 :
        text = sys.argv[1]
    elif len(sys.argv) == 3 :
        title = sys.argv[1]
        text = sys.argv[2]
    elif len(sys.argv) == 4 :
        if sys.argv[2].isdigit():
            text = sys.argv[1]
            keyWordLimit = int(sys.argv[2])
            abstractLength = int(sys.argv[3])
        else:
            title = sys.argv[1]
            text = sys.argv[2]
            keyWordLimit = int(sys.argv[3])
    else:
        title = sys.argv[1]
        text = sys.argv[2]
        keyWordLimit = int(sys.argv[3])
        abstractLength = int(sys.argv[4])
    
    dictFilePath = os.path.join(os.path.dirname(__file__) + '/../../../dict/all.txt')
    stopWordsFilePath = os.path.join(os.path.dirname(__file__) + '/../../../dict/stopword.txt')
    subjectTerms = loadSubjectTerms(dictFilePath)
    stopWords = loadStopwords(stopWordsFilePath)
    
    extractResult = extract(title, text, stopWords, subjectTerms, keyWordLimit)
    abstractResult = abstract(title, text, stopWords)
    result = {
        'abstract' : abstractResult,
        'keywords' : extractResult
    }
    result = json.dumps(result)
    print(str(result))