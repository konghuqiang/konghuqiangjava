import tornado.web
import tornado.ioloop
import suds
from  suds  import  client
from phonetutils.phone import *
import json
from phonetutils.cacheutils import CacheService
class  IndexHandler(tornado.web.RequestHandler):
       #get请求  self:当前对象
       def  get(self):
            print("接受到用户的get请求 ")
            #写一个消息
            self.render("login.html",failmsg=None);

class  UserHandler(tornado.web.RequestHandler):
      def   post(self):
            print("接受到用户的user_*请求")

            method=self.get_argument("action")
            print("method-->",method)
            if  method=="login":

               #请求的头的信息为: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36
               #请求的头的信息为: Mozilla/5.0 (Linux; Android 8.0.0; DUK-AL20 Build/HUAWEIDUK-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.91 Mobile Safari/537.36
               #anroid  mac windowmobile
               username=self.get_argument("username")
               userphone=self.get_argument("userphone")
               print(username,userphone)

               url="http://127.0.0.1:8100/userdataservice/user?wsdl"
               service=suds.client.Client(url)
               msg=service.service.checkUserLogin(username,userphone)
               print("msg-->",msg)

               # 怎么来区分是浏览器还是手机的请求
               # 得到请求的头
               headsInfo = self.request.headers

               # print("headsInfo-->",headsInfo)
               hinfo = headsInfo["User-Agent"]
               print("请求的头的信息为:", hinfo)
               val = checkPCorMobile(hinfo)
               print("val",val)
               jsonDatas=""
               if  msg=='登录成功':
                   cacheService=CacheService()
                   jsonDatas=cacheService.getMenuData("tmenudata")
                   print(jsonDatas)
                   if val=="PC":
                      self.render("index.html",contentdata=jsonDatas)
                   else:
                       #json数据格式
                      self.finish({"msg":"success","contentdata":jsonDatas})
               else:
                   if val == "PC":
                       self.render("login.html",failmsg=msg)
                   else:
                       self.finish({"msg": "fail"})
            elif method=="register":
                self.render("register.html")



#设置配置项
settings={
    "template_path":"templates",
    "static_path":"static",

}


#创建一个应用对象
#包含路由,请求的动作名对应着业务类
app = tornado.web.Application([(r'/', IndexHandler),
                               (r'/user',UserHandler),
                               ],**settings)



if __name__=="__main__":
    app.listen(8023)
    tornado.ioloop.IOLoop.current().start()