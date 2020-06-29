# Virtual-Jewelry-Tryon-Platform-for-SpringBoot

基于Android的虚拟饰品试穿平台，服务端部分
## 前言
由于前期设计及和后期实现的差别问题，将饰品定义为能够在面部穿戴的物品，包括但不限于耳环，帽子眼镜等物品。测试数据中目前只存在帽子和眼镜种类。


## 准备
服务器端同时支持http和https的访问，

更改/Virtual-Jewelry-Tryon-Platform-for-SpringBoot/src/main/resources/application.properties文件设置配置，也可以使用命令行更改配置
```
server.http.port= 8081		//http端口号
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 		//jdbc
spring.datasource.url= jdbc:mysql://localhost:3307/virtual_fitting_room?characterEncoding=utf8&useSSL=false&serverTimezone=UTC 		//数据库地址
spring.datasource.username=root 		//数据库用户名
spring.datasource.password=root			//数据库密码
spring.jpa.show-sql=false		//jpa是否显示sql语句
spring.jpa.hibernate.ddl-auto=update		//数据库初始化模式
spring.main.allow-bean-definition-overriding=true
spring.datasource.initialization-mode=never

server.port=8443		//https端口号
server.ssl.key-store=classpath:.keystore
server.ssl.key-store-password=111111
server.ssl.key-store-type=JKS
server.ssl.key-alias=tomcatHttps
server.ssl.enabled=true

com.Hashqi.resourcepath=file:E:\\Virtual-Jewelry-Tryon-Platform-for-SpringBoot		//model和avatar的绝对路径（外部资源）
```

更改/Virtual-Jewelry-Tryon-Platform-for-SpringBoot/src/main/resources/data.sql文件初始化数据库，同时更改上述文件
```
spring.jpa.hibernate.ddl-auto=create
spring.datasource.initialization-mode=always
```

## 运行
下载源包后使用命令进行清理和打包

```
mvn package clean -Dmaven.test.skip=true

mvn package -Dmaven.test.skip=true
```

运行jar包
```
java -jar xxx.jar 
```

后台运行jar包并输出log到文件
```
nohup java -jar xxx.jar > log.log 2>&1 &
```