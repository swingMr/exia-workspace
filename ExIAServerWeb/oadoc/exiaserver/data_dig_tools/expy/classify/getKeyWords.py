# -*- encoding:utf-8 -*-

import os
import fnmatch
import jieba
import jieba.analyse
from optparse import OptionParser
import queryDomainByRule
import sys

def dictLoad():
    allWordsFilePath = os.path.join(os.path.dirname(__file__) + '/../../../dict/all.txt')
    stopWordsFilePath = os.path.join(os.path.dirname(__file__) + '/../../../dict/stopword.txt')
    jieba.load_userdict(allWordsFilePath)
    jieba.analyse.set_stop_words(stopWordsFilePath)

#获取相关文档主题词的方法。传入参数：title:标题，content:内容，keyWordNum:主题词数量
def getKeyWords(title,content,keyWordNum,ruleList):
    # 把ExKE的词做成过滤字典
    exkewords = {}
    allWordsFilePath = os.path.join(os.path.dirname(__file__) + '/../../../dict/all.txt')
    ketop = open(allWordsFilePath, 'rb')
    for eachWord in ketop:
        exkewords[eachWord.strip().decode('utf-8', 'ignore')] = eachWord.strip().decode('utf-8', 'ignore')
    ketop.close()
    words = []
    
    if title is not None:
        tfidf = jieba.analyse.extract_tags(title, topK=keyWordNum, withWeight=True)
        for word_weight in tfidf:
            if word_weight[0] in exkewords:
                words.append(word_weight[0])

    if ruleList is not None:
        for row in ruleList:
            ruleContent = row['rule_content']
            resg = queryDomainByRule.getDomainByRule(title,ruleContent)
            if(resg=="True" and resg not in words):
                words.append(row["concept_name"])

    num = len(words)
    if(num<keyWordNum) and content is not None:
        contentWord = jieba.analyse.extract_tags(content, topK=keyWordNum+30, withWeight=True)
        for con_word_weight in contentWord:
            if con_word_weight[0] not in words  and len(words)<keyWordNum and con_word_weight[0] in exkewords:
                words.append(con_word_weight[0])

    return words

if __name__ == '__main__':
    content = sys.argv[1];
    dictLoad()
    resultList = getKeyWords(None,content,None,None)
    print (resultList)
