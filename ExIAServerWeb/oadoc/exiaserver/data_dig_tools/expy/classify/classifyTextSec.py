#!/usr/bin/env python
# encoding: utf-8

import os
import random
import re
import jieba
import math
import sys
from datetime import datetime
import codecs
import json
class ClassifyTextSec(object):
    #input_dir:语料所在文件夹
    #train_data_file:训练集文件名
    def __init__(self, model_name, model_dir,text):
        self.title = text
        self.tag_data_file = codecs.open(model_dir+'/'+model_name+'.tag', 'r','gbk')
        self.unique_words = []
        # 每一个单词都使用一个数字类型的id表示，python索引的时候才会快一些
        self.word_ids = {}
        self.model_data_file = open(model_dir+'/'+model_name+'.model', 'r')
        # 每个类别的先验概率
        self.class_probabilities = {}
        # 拉普拉斯平滑，防止概率为0的情况出现
        self.laplace_smooth = 0.1
        # 模型训练结果集
        self.class_word_prob_matrix = {}
        # 当某个单词在某类别下不存在时，默认的概率（拉普拉斯平滑后）
        self.class_default_prob = {}
        # 测试数据单词
        self.test_words=[]
        # 所有单词
        self.unique_words_num = {}
        # 实际的新闻分类
        self.real_classes = []
        # 预测的新闻分类
        self.predict_classes = []
        self.result_data_file = open(model_dir + '/' + model_name + '.result', 'w')
        #测试标题分词
        self.test_data = []
        # 停用词典
        self.stopwords = {}
        stopWordsFilePath = os.path.join(os.path.dirname(__file__) + '/../../../dict/stopword.txt')
        fstop = open(stopWordsFilePath, 'rb')
        for eachWord in fstop:
            self.stopwords[eachWord.strip().decode('utf-8', 'ignore')] = eachWord.strip().decode('utf-8', 'ignore')
        fstop.close()
    def loadTag(self):
        line_num = 0
        line = self.tag_data_file.readline().strip()
        self.unique_words=json.loads(line.replace("'","\"").replace("\\",""))
        line = self.tag_data_file.readline().strip()

        self.word_ids=json.loads(line.replace("'","\"").replace("\\",""))
        # while len(line) > 0:
        #     line_num +=1
        #     words = line.split()
        #     for word in words:
        #         # if "ERROR" in word:
        #         #     continue
        #         wr = word.split("#")[0]
        #         self.unique_words.append(wr)
        #         self.word_ids[wr] = word.split("#")[1]
        #     line = self.tag_data_file.readline().strip()

    def loadModel(self):
        # 从模型文件的第一行读取类别的先验概率
        class_probs = self.model_data_file.readline().split('#')
        for cls in class_probs:
            arr = cls.split()
            if len(arr) == 3:
                self.class_probabilities[arr[0]] = float(arr[1])
                self.class_default_prob[arr[0]] = float(arr[2])
        # 从模型文件读取单词在每个类别下的概率
        line = self.model_data_file.readline().strip()
        while len(line) > 0:
            arr = line.split()
            assert(len(arr) % 2 == 1)
            assert(arr[0] in self.class_probabilities)
            self.class_word_prob_matrix[arr[0]] = {}
            i = 1
            while i < len(arr):
                word_id = int(arr[i])
                probability = float(arr[i+1])
                if word_id not in self.unique_words_num:
                    self.unique_words_num[word_id] = 1
                self.class_word_prob_matrix[arr[0]][word_id] = probability
                i += 2
            line = self.model_data_file.readline().strip()
        # print (len(self.class_probabilities), " classes loaded!", len(self.unique_words_num), "words!")
    def prepare(self,text):
        file_num = 0
        self.title=text
        words=self.title.split()
        if(len(self.test_words)>0):
            for t in self.test_words:
                self.unique_words.remove(t)
                del self.word_ids[t]
        self.test_data=[]
        self.test_words=[]
        for word in words:
            tags = jieba.cut(word, cut_all=False)
            for w in tags:
                if w not in self.word_ids and w not in self.stopwords:
                    self.unique_words.append(w)
                    # 可以取Hash，这里为了简便期间，直接使用当前数组的长度（也是唯一的）
                    self.word_ids[w] = len(self.unique_words)
                    self.test_words.append(w)
                if w not in self.stopwords:
                    self.test_data.append(str(self.word_ids[w]))

    def caculate(self):
        # 读取测试数据集
        # 预测当前行（一个新闻）属于各个分类的概率
        class_score = {}
        for key in self.class_probabilities.keys():
            class_score[key] = math.log(self.class_probabilities[key])
        for word_id in self.test_data:
            word_id = int(word_id)
            if word_id not in self.unique_words_num:
                continue
            for class_id in self.class_probabilities.keys():
                if word_id not in self.class_word_prob_matrix[class_id]:
                    class_score[class_id] += math.log(self.class_default_prob[class_id])
                else:
                    class_score[class_id] += math.log(self.class_word_prob_matrix[class_id][word_id])
        # 对于当前新闻，所属的概率最高的分类
        max_class_score = max(class_score.values())
        ss = sorted(class_score.items(), key=lambda x: x[1],reverse=True)
        # print(ss)
        self.predict_classes = []
        self.predict_classes.append(ss[0])
        self.predict_classes.append(ss[1])

#         print (self.predict_classes)

    def createResult(self):
        output_file = self.result_data_file
        output_file.write(str(self.predict_classes[0]))
        self.result_data_file.close()

    def predict(self):
        # self.loadModel()
        self.caculate()
        self.model_data_file.close()
        return self.predict_classes

    def classifyTextSec(self,text):
        self.loadTag()
        self.loadModel()
        self.prepare(text)
        self.predict()
        self.createResult()
if __name__ == '__main__':
    c = datetime.now()
    dp = ClassifyTextSec(sys.argv[1],sys.argv[2],sys.argv[3])
    dp.classifyTextSec(sys.argv[3])
    d = datetime.now()
    print((d - c).seconds)