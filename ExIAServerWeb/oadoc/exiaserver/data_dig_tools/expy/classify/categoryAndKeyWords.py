import pymysql
import classifyTextSec
import getKeyWords
from datetime import datetime
import json
import sys

class MysqlHandle(object):

    def __init__(self, host, user, passwd, db, tableName, MetaDataIds):
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
        self.tableName = tableName
        self.MetaDataIds = MetaDataIds

    def __del__(self):
        # 关闭连接
        self.cursor.close()
        self.connect.close()

    def getData(self):
        # 查询数据
        sql = "SELECT * FROM "+self.tableName +" where workitem_id in "+self.MetaDataIds

        self.cursor.execute(sql)
        result = []
        index = self.cursor.description
        for res in self.cursor.fetchall():
            row = {}
            for i in range(len(index) - 1):
                row[index[i][0]] = res[i]
            result.append(row)
        #print (result)
#         print('共查找出', self.cursor.rowcount, '条数据')
        return result

if __name__ == '__main__':
    paremeter = eval(sys.argv[1]);
#     paremeter=json.loads(paremeterStr);
    host = paremeter["host"]
    user = paremeter["user"]
    passwd = paremeter["passwd"]
    dbName = paremeter["dbName"]
    tableName = paremeter["tableName"]
    MetaDataIds = str(paremeter["ids"])
    modelName = paremeter["modelName"]
    modelDir = paremeter["modelDir"]
    MetaDataIds = MetaDataIds.replace("[","(").replace("]",")")
#     print (MetaDataIds)
#     MetaDataIds = "('3413','3414','3415')"
    mqHandle = MysqlHandle(host,user,passwd,dbName,tableName,MetaDataIds)
    dataList =[]
    # 从数据库获取数据
    result = mqHandle.getData()
    # file_num=0
    # a = datetime.now()
    getKeyWords.dictLoad()
    dp = classifyTextSec.ClassifyTextSec(modelName, modelDir, "")
    dp.loadTag();
    dp.loadModel()
    for row in result:
        data = {}
        id = row['workitem_id']
        title = row['res_title']
        content = row['res_content']
 
        dp.prepare(title)
        domain = dp.predict()
        category = []
        category.append(domain[0][0].split(".")[0])
        category.append(domain[1][0].split(".")[0]) 
 
        keyWords = getKeyWords.getKeyWords(title, content, 20,None)
#         keyWordStr = ''
#         for word in keyWords:
#             keyWordStr += word + ";"
 
        data["id"]=id
        data["title"]=title
        data["category"]=category
        data["keyWords"]=keyWords
        dataList.append(data)
    print(str(dataList))
