import pymysql
from datetime import datetime
from elasticsearch import Elasticsearch
from elasticsearch import helpers
import getKeyWords
import sys
import httpPost

class MysqlHandle(object):

    def __init__(self, host, port, user, passwd, db):
        # 连接数据库
        self.connect = pymysql.Connect(
            host=host,
            port=3306,
            user=user,
            passwd=passwd,
            db=db,
            charset='utf8'
        )

        # 获取游标
        self.cursor = self.connect.cursor()

    def __del__(self):
        # 关闭连接
        self.cursor.close()
        self.connect.close()

    def getData(self,tableName,id):
        # 查询数据
        sql = "SELECT * FROM "+tableName + " where MetaDataId='" +id+"'"

        self.cursor.execute(sql)
        result = []
        index = self.cursor.description
        for res in self.cursor.fetchall():
            row = {}
            for i in range(len(index)):
                if index[i][0] == "keywords" or index[i][0] == "category":
                    keyWords = []
                    words = []
                    if res[i]:
                        words = res[i].split(";")
                    for w in words:
                        keyWords.append(w)
                    row[index[i][0]] = keyWords
                else:
                    row[index[i][0]] = res[i]
            result.append(row)
        #print (result)
#         print('共查找出', self.cursor.rowcount, '条数据')
        return result

    def updateDate(self,data,tableName):
        # 修改数据
        sql = "UPDATE " + tableName + " SET keywords = '%s',tags='%s' WHERE MetaDataId = '%s' "
        # data = ("科学技术", '1938')
        self.cursor.execute(sql % data)
        self.connect.commit()
        # print('成功修改', self.cursor.rowcount, '条数据')
    
    #导入数据
    def insetrData(self,elasHost,dbName,tableName,result):
        # 连接elasticsearch,默认是9200
        es = Elasticsearch(elasHost,timeout=5000)
#         mysqlHandle = wcmKeyWordsImport.MysqlHandle(host, '', user, passwd, dbName)
        
        # 创建ACTIONS
        ACTIONS = []
        for i in range(len(result)):
            res  = result[i]
            action = {
                "_index": dbName,
                "_type": tableName,
                "_source": res
            }
            ACTIONS.append(action)
        helpers.bulk(es, ACTIONS)
    
    def deleteData(self,elasHost,dbName,id):
        es = Elasticsearch(elasHost, timeout=5000)
        es.delete_by_query(dbName, body={"query": {"match": {"MetaDataId": id}}})

if __name__ == '__main__':
    paremeter = eval(sys.argv[1]);
#     paremeter=json.loads(paremeterStr);
    host = paremeter["host"]
    user = paremeter["user"]
    passwd = paremeter["passwd"]
    dbName = paremeter["dbName"]
    pare = paremeter["paremeter"]
    actCode = paremeter["actCode"]
    elasHost = paremeter["elasHost"]
    mqHandle = MysqlHandle(host, '', user, passwd, dbName)
    
    for p in pare:
        MetaDataId=p["id"]
        tableName=p["tableName"]
        result = mqHandle.getData(tableName,MetaDataId)
    #     a = datetime.now()
        for row in result:
            id = row['MetaDataId']
            title = row['title']
            content = row['content']
            
            values = {}
            values['title']=title
            values['text']=content
            
            try:
                keyUrl = 'http://127.0.0.1:8001/ExIAServer/services/calculate/abstract'
                jsonStr = eval(httpPost.http_post(keyUrl,values));
                keywords = jsonStr["data"]["keywords"]
                keyWordStr=''
                for kw in keywords:
                    word = kw["word"]
                    keyWordStr += word + ";"
                    
                tagUrl = 'http://127.0.0.1:8001/ExIAServer/services/calculate/gentag'    
                target = str(eval(httpPost.http_post(tagUrl,values))["data"]).replace("'","\"");
                data = (keyWordStr,target,id)
                mqHandle.updateDate(data,tableName)
            except:
                continue
         
        if actCode=="update_tag_index":
            mqHandle.deleteData(elasHost,dbName,MetaDataId)
            newResult = mqHandle.getData(tableName,MetaDataId)
            mqHandle.insetrData(elasHost,dbName,tableName,newResult)
    print("success")