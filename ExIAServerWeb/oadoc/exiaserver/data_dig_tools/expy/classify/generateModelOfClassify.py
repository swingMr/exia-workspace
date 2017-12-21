#!/usr/bin/env python
# encoding: utf-8
"""
    author: royguo1988@gmail.com
"""
import os
import random
import re
import jieba
import sys
class GenerateModelOfClassify(object):
    """处理原始数据，为机器学习模型的训练作准备"""
    def __init__(self,model_name, input_dir, ouput_dir):
        self.train_path = ouput_dir+'/'+model_name+'.train'
        self.input_dir = input_dir
        self.train_data_file = open(ouput_dir+'/'+model_name+'.train','w')
        self.tag_data_file = open(ouput_dir+'/'+model_name+".tag",'w')
        self.unique_words = []
        # 每一个单词都使用一个数字类型的id表示，python索引的时候才会快一些
        self.word_ids = {}
        self.train_data_file_red = open(ouput_dir+'/'+model_name+'.train', 'r')
        self.model_file = open(ouput_dir+'/'+model_name+'.model', 'w')
        # 存储每一种类型出现的次数
        self.class_count = {}
        # 存储每一种类型下各个单词出现的次数
        self.class_word_count = {}
        # 唯一单词总数
        self.unique_words_num = {}
        # ~~~~~~~~~~ NavieBayes参数 ~~~~~~~~~~~~#
        # 每个类别的先验概率
        self.class_probabilities = {}
        # 拉普拉斯平滑，防止概率为0的情况出现
        self.laplace_smooth = 0.1
        # 模型训练结果集
        self.class_word_prob_matrix = {}
        # 当某个单词在某类别下不存在时，默认的概率（拉普拉斯平滑后）
        self.class_default_prob = {}

    def prepare(self):
        file_num = 0
        output_file = self.train_data_file
        for file_name in os.listdir(self.input_dir):
            category =file_name
            # 读取文件获得词组
            words = []
            with open(self.input_dir + '/' + file_name,'rb') as f:
                words = f.read().decode('utf-8').split()
            output_file.write(category + ' ')

            for word in words:
                tags = jieba.cut(word, cut_all=False)
                for w in tags:
                    if w not in self.word_ids:
                        self.unique_words.append(w)
                        # 可以取Hash，这里为了简便期间，直接使用当前数组的长度（也是唯一的）
                        self.word_ids[w] = len(self.unique_words)
                    output_file.write(str(self.word_ids[w]) + " ")
            output_file.write("#"+file_name+"\n")
            # 原始文件较多，需要交互显示进度
            file_num += 1
            if file_num % 100 == 0:
                print (file_num,' files processed')
        print (file_num, " files loaded!")
        print (len(self.unique_words), " unique words found!")
        self.train_data_file.close()
    def createTag(self):
        output_file=self.tag_data_file
        for w in self.unique_words:
            try:
                output_file.write(w+"#"+str(self.word_ids[w]) + " ")
            except :
                print('发生了一个错误')
        self.tag_data_file.close()
    def loadData(self):
        line_num = 0
        line = self.train_data_file_red.readline().strip()
        while len(line) > 0:
            words = line.split('#')[0].split()
            category = words[0]
            if category not in self.class_count:
                self.class_count[category] = 0
                self.class_word_count[category] = {}
                self.class_word_prob_matrix[category] = {}
            self.class_count[category] += 1
            for word in words[1:]:
                word_id = int(word)
                if word_id not in self.unique_words_num:
                    self.unique_words_num[word_id] = 1
                if word_id not in self.class_word_count[category]:
                    self.class_word_count[category][word_id] = 1
                else:
                    self.class_word_count[category][word_id] += 1
            line = self.train_data_file_red.readline().strip()
            line_num += 1
            if line_num % 100 == 0:
                print (line_num,' lines processed')
        print (line_num,' training instances loaded')
        print (len(self.class_count), " categories!", len(self.unique_words_num), "words!")
    def computeModel(self):
        # 计算P(Yi)
        news_count = 0
        for count in self.class_count.values():
            news_count += count
        for class_id in self.class_count.keys():
            self.class_probabilities[class_id] = float(self.class_count[class_id]) / news_count
        # 计算P(X|Yi)  <===>  计算所有 P(Xi|Yi)的积  <===>  计算所有 Log(P(Xi|Yi)) 的和
        for class_id in self.class_word_count.keys():
            # 当前类别下所有单词的总数
            sum = 0.0
            for word_id in self.class_word_count[class_id].keys():
                sum += self.class_word_count[class_id][word_id]
            count_Yi = (float)(sum + len(self.unique_words_num)*self.laplace_smooth)
            # 计算单个单词在某类别下的概率，存储在结果矩阵中，所有当前类别没有的单词赋予默认概率(即使用拉普拉斯平滑)
            for word_id in self.class_word_count[class_id].keys():
                self.class_word_prob_matrix[class_id][word_id] = \
                    (float)(self.class_word_count[class_id][word_id]+self.laplace_smooth) / count_Yi
            self.class_default_prob[class_id] = (float)(self.laplace_smooth) / count_Yi
            print (class_id,' matrix finished, length = ',len(self.class_word_prob_matrix[class_id]))
        return
    def saveModel(self):
        # 把每个分类的先验概率写入文件
        for class_id in self.class_probabilities.keys():
            self.model_file.write(class_id)
            self.model_file.write(' ')
            self.model_file.write(str(self.class_probabilities[class_id]))
            self.model_file.write(' ')
            self.model_file.write(str(self.class_default_prob[class_id]))
            self.model_file.write('#')
        self.model_file.write('\n')
        # 把每个单词在当前类别的概率写入文件
        for class_id in self.class_word_prob_matrix.keys():
            self.model_file.write(class_id + ' ')
            for word_id in self.class_word_prob_matrix[class_id].keys():
                self.model_file.write(str(word_id) + ' ' \
                     + str(self.class_word_prob_matrix[class_id][word_id]))
                self.model_file.write(' ')
            self.model_file.write('\n')
        return
    def createModle(self):
        self.loadData()
        self.computeModel()
        self.saveModel()
        self.train_data_file_red.close()
        self.model_file.close()
        os.remove(self.train_path)
    def generateModel(self):
        self.prepare()
        self.createTag()
        self.createModle()
if __name__ == '__main__':
    dp = GenerateModelOfClassify(sys.argv[1],sys.argv[2],sys.argv[3])
    dp.generateModel()
