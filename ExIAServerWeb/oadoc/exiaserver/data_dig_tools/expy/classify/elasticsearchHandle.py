from datetime import datetime
from elasticsearch import Elasticsearch
from elasticsearch import helpers
import sys
import wcmKeyWordsImport


#导入数据
def insetrData(elasHost,host,dbName,tableName,user,passwd,MetaDataId):
    # 连接elasticsearch,默认是9200
    es = Elasticsearch(elasHost,timeout=5000)
    mysqlHandle = wcmKeyWordsImport.MysqlHandle(host, '', user, passwd, dbName)
    result = mysqlHandle.getData(tableName,MetaDataId)
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

def deleteData(elasHost,dbName,id):
    es = Elasticsearch(elasHost, timeout=5000)
    es.delete_by_query(dbName, body={"query": {"match": {"MetaDataId": id}}})


if __name__ == '__main__':
    paremeter = eval(str(sys.argv[1]));
#     paremeter=json.loads(paremeterStr);
    host = paremeter["host"]
    user = paremeter["user"]
    passwd = paremeter["passwd"]
    dbName = paremeter["dbName"]
    pare = paremeter["paremeter"]
    actCode = paremeter["actCode"]
    elasHost = paremeter["elasHost"]
    
    for p in pare:
        MetaDataId=p["id"]
        tableName=p["tableName"]
        deleteData(elasHost,dbName,MetaDataId)
        if actCode=="update_index":
            insetrData(elasHost,host,dbName,tableName,user,passwd,MetaDataId)
        
    print("success")