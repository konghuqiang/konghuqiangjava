import suds
from suds import client
from phonetutils.phone import *
import json

class CacheService():

    cachedata={}
    def getMenuData(self,key):
        if key in self.cachedata:
            print("缓存中有数据****")
            return self.cachedata[key]
        else:
            print("缓存中没有数据****")
            url="http://127.0.0.1:8100/userdataservice/user?wsdl"
            service=suds.client.Client(url)
            data=service.service.queryGirdMenuData()
            print("data-->",data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.cachedata[key] = jsonDatas
            return jsonDatas

