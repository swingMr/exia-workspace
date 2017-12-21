#!/usr/bin/env python
# encoding: utf-8

import sys
import os
import json
import jieba
import jieba.posseg as pseg
import jieba.analyse

def loadSubjectTerms(path):
    with open(path, 'rb') as f:
        subjectTerms = list(filter(lambda x: x, map(lambda x: x.strip().decode('utf-8', 'ignore'), f.readlines())))  
    return frozenset(subjectTerms)

def participle(text,subjectTerms=None):
    segResult = pseg.cut(text)
    res = {}
    for w in segResult:
        if subjectTerms!=None and subjectTerms.__contains__(w.word):
            if res.get(w.word)==None :
                res[w.word] = []
            res[w.word].append(w.flag);
    return res

if __name__ == '__main__':
    hasPos = False;
    text = "";
    if len(sys.argv) > 1 :
        text = sys.argv[1]
    if len(sys.argv) > 2 :
        hasPos = str(sys.argv[2]).lower() == str(True).lower()
        
    dictFilePath = os.path.join(os.path.dirname(__file__) + '/../../../dict/all.txt')
    subjectTerms = loadSubjectTerms(dictFilePath)
    
    res = participle(sys.argv[1], subjectTerms)
    res = [ ({"word":k} if hasPos==False else {"word":k, "part": list(set(v))}) for (k,v) in res.items()]
    
    print(str(json.dumps(res)))
    
    