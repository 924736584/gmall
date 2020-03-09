# gmall

## 问题汇总：
### 1.添加mybaties
### 2.添加tk.mapper 通用mapper
实现方式:1.引入pom
```xml
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>1.2.3</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
2.继承模板
```java
public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUser();
}
```
3.添加扫描
```java
//添加的mapperScan要改为tk.mapper下的包
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages ="com.deyuan.gmall.user.mapper")
```
4使用;
```java
userMapper.selectAll();...
```
学会泛型编程;
通用Mapper的条件查询
```java
  @Override
    public List<UmsMemberReceiveAddress> getReceiveAddress(String memberId) {
//        Example e=new Example(UmsMemberReceiveAddress.class);
//        e.createCriteria().andEqualTo("memberId",memberId);
        UmsMemberReceiveAddress e=new UmsMemberReceiveAddress();
        e.setMemberId(Long.parseLong(memberId));
      return  memberReceiveAddressMapper.select(e);
//查看sql语句调整日志级别：
//application.properties
//logging.level.root=debug
```
### parent依赖抽取

作用：版本控制版本统一化管理：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.1.12.RELEASE</version>
        <relativePath/>
    </parent>

    <groupId>com.deyuan.gmall</groupId>
    <artifactId>gmall-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <properties>
        <project.buid.sourceEncoding>UTF-8</project.buid.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>

        <fastjson.version>1.2.46</fastjson.version>
        <dubbo-starter.version>0.2.1.RELEASE</dubbo-starter.version>
        <dubbo.version>2.6.3</dubbo.version>
        <zkclient.version>0.10</zkclient.version>
        <mybaties.version>1.3.1</mybaties.version>
        <nekohtml.version>1.9.20</nekohtml.version>
        <xml-apis.version>1.4.01</xml-apis.version>
        <batik-ext.version>1.9.1</batik-ext.version>
        <jsoup.version>1.11.2</jsoup.version>
        <httpclient.version>4.5.5</httpclient.version>
        <commons-long3.version>3.7</commons-long3.version>
        <mapper-starter.version>1.2.3</mapper-starter.version>
        <jedis.version>2.9.0</jedis.version>
        <jest.version>4.5.1</jest.version>
        <jna.version>4.5.1</jna.version>
        <beanUtils.version>1.9.3</beanUtils.version>
        <logging.version>2.2.2.RELEASE</logging.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${fastjson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>${dubbo.version}</version>
        </dependency>
        <dependency>
            <groupId>com.101tec</groupId>
            <artifactId>zkclient</artifactId>
            <version>${zkclient.version}</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>${dubbo-starter.version}</version>
        </dependency>
        <dependency>
            <groupId>net.sourceforge.nekohtml</groupId>
            <artifactId>nekohtml</artifactId>
            <version>${nekohtml.version}</version>
        </dependency>
        <dependency>
            <groupId>xml-apis</groupId>
            <artifactId>xml-apis</artifactId>
            <version>${xml-apis.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.xmlgraphics</groupId>
            <artifactId>batik-ext</artifactId>
            <version>${batik-ext.version}</version>
        </dependency>
         <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>${jsoup.version}</version>
        </dependency>
         <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>${httpclient.version}</version>
        </dependency>
         <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>${commons-long3.version}</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/tk.mybatis/mapper-spring-boot-starter -->
         <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>${jedis.version}</version>
        </dependency>
         <dependency>
            <groupId>net.java.dev.jna</groupId>
            <artifactId>jna</artifactId>
            <version>${jna.version}</version>
        </dependency>
         <dependency>
            <groupId>commons-beanutils</groupId>
            <artifactId>commons-beanutils</artifactId>
            <version>${beanUtils.version}</version>
        </dependency>
    </dependencies>
</project>
```
### 抽取api工程

作用管理所有的bean与service后期的api都在这个文件维护
tk-mybaties跟着bean走，要使用tk-mybaties引入这个工程就可以了
### 抽取until工程 
项目中通用的框架是所有应用工程需要引入的包
例如springboot ，common-langs,common-beanutils

2.基于soa的框架理念，项目分为web前端controller
JSP thymeleaf,cookie工具类

3.基于soa的架构理念，项目分为web后端 service

Mybaties ,mysql,redies

gmall-common-util
```xml
<dependency>
           <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
           </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

        	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

         <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
         </dependency>

                <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
                </dependency>

              <dependency>
            <groupId>commons-beanutils</groupId>
            <artifactId>commons-beanutils</artifactId>
              </dependency>

            <dependency>
            <groupId>apache-codec</groupId>
            <artifactId>commons-codec</artifactId>
            </dependency>
```
gmall-service-util
```xml
<dependency>
		<groupId>com.deyuan.gmall</groupId>
		<artifactId>gmall-common-util</artifactId>
		<version>1.0-SNAPSHOT</version>
</dependency>
    	<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
		</dependency>
	 <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
	 </dependency>
```
### soa面向服务（以dubbo为基础）
1.dubbo的soa的工作原理喝springcoloud类似
2.dubbo喝springcolud的区别在于dubbo协议通讯，sc 是由http协议（rest）风格
3.dubbo有一个注册中心的客服端在时时同步组测中心的服务信息
4dubbo有一个javaweb的监控中心，负责监控，还可以配置负载均衡
## dubbo搭建

dubbo环境搭建与zookeeper安装：

### 1.安装tomcat，将dubbo-admin部署到tomcat中

首先需要先解压zookeeper然后启动tomcat
否者启动就会报错
将user项目拆分未user-server 和user-web
### 2,引入dubbo框架
将服务的@Service配置为dubbo的@server

### 3.配置服务端的dubbo配置

```properties
#dubbo的配置
#dubbo中的服务名称
dubbo.application.name=user-service
#dubbo的协议名称
dubbo.protocol.name=dubbo
#dubbo注册中心zookeeper
dubbo.registry.address=192.168.36.131:2181
dubbo.registry.protocol=zookeeper
#dubbo的服务扫描路径
dubbo.scan.base-packages=com.deyuan.gmall
```
### 4.启动服务

（所有的接口都必须序列化不序列化会报错）可以引入一个 依赖解决这个报错

```xml
 <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>2.13.0</version>
        </dependency>
```

### 客户端使用

#### 1.将@Autowired改为 @Reference

#### 2，dubbo在进行dubbo通讯的时候需要实现序列化接口（封装数据对象）

#### 3，dubbo的服务consumer在三之类每间隔一秒进行一次重新访问，默认一秒钟超时，三次访问之后直接抛异常，在开发阶段可以将consumer时间延长，方便断点调试

#### 设置超时时间

dubbo.consumer.timeout=600000

#### 置是否检查服务存在

dubbo.consumer.check=false

spu与sku
spu：（Satndard Product Unit）标准化产品单元，上坪信息集合的最小单位，是一组可反复复用易检索的标准化集合，该集合描述了一个产品的特性。
sku：（stock Keeping Unilt）(库存单元) ，即库存进出计量的基本单位，可以是以件，盒等为单位sku这是对大型连锁超市DC配送中心物流管理的一个必要的方法
现在已经被引申为产品统一编写的简称

## 前后端分离

1，准备阶段
渲染：thymeleaf，JSP，freemarker
2.前后端分离
JVM,spring,maven,idea
Nodejs,vue,npm,vscode
3.安装nodeJs
查看是否成功
node -v
安装npm
npm -install
解压前端项目（gmall-admin）
conf
配置前端服务的ip和前端访问数据的后端的服务的ip地址
dev.env.js 前端访问后端的数据服务地址
index.js 前端的服务器端口
用npm命令编译和启动前端项目
npm run  dev

### 跨域
数据传到前端没有数据读出：原因在控制层添加
@CrossOrigin注解解决实际上是添加头信息

![image-20200212220727525](E:\SpringBoot_WorkSpace\gmall\image-20200212220727525.png)

### 分布式文件存储
#### 搭建：

0.
安装环境：
```shell script
yum -y install gcc-c++ -y
yum -y install libevent
yum install perl*
yum -y install zlib zlib-devel pcre pcre-devel gcc gcc-c++ openssl opssl-devel libevent libevent-devel perl unzip net-tools wget

```
1.fdfs的依赖库
Libfastcommon
1.解压，
2.进入文件libfastcommon执行./make.sh ，在执行./make.sh install
3.注意将/usr/lib64/libfastcommon.so 拷贝到 /usr/lib中


2.fastdfs 软件（tracker ,stroage）
配置tracker
配置stroage
tracker环境:
依赖：Gcc,libevent,perl
详见：
https://blog.csdn.net/prcyang/article/details/89946190

3.FastDFS-neinx-module
fdfs整合


4.nginx
依赖：pcre-devel zlib-devel

配置环境遇到的问题：
一.ngx_fastdfs_module 找不到
巨坑！！！！！！！！
解决思路：
1.检查是否插件名称写错了
2.nginx -V 查看模块是否安装
3.如果都有不报错检查Config配置文件
当前问题：
::原因是参考文档错误导致配置文件（/usr/local/fastdfs-nginx-module-1.20/src.config）错误

二.nginx启动问题
1.检查端口是否被占用(#netstat -ntp) ;
2.检查文件配置和进程启动（#nginx -t/ #ps -elf|grep nginx）;
正常一般是有一个主进程，和其他工作进程(至少一个)
当前问题没有工作进程：
解决 方法：
将(/usr/local/fastdfs-5.11/conf/mime.types)拷贝到/etc/fdfs中
还是不能解决。。修改了mod_nginx_module.conf文件中放开了[group1]的配置
一定不要忘了配置完重启fastdfs相关的服务
然后就可以启动nginx了
```shell script
./configure \
--prefix=/usr/local/nginx \
--sbin-path=/usr/local/nginx/sbin/nginx \
--conf-path=/usr/local/nginx/conf/nginx.conf \
--error-log-path=/usr/local/nginx/log/error.log \
--http-log-path=/usr/local/nginx/log/access.log \
--pid-path=/usr/local/nginx/log/pid/nginx.pid \
--lock-path=/usr/local/nginx/log/lock/subsys/nginx \
--add-module=/usr/local/fastdfs-nginx-module-1.20/src
```
## Redis

缓存安装：
Redis整合项目：
1.加载redis客户端Jedis
引入依赖
可以通过SpringBoot的官方工具进行redies的操作
通过配置类来完成初始化与得到接口

```java
package com.deyuan.gmall.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private JedisPool jedisPool;

    public void initPool(String host, int port, int database) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxIdle(30);
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxWaitMillis(10 * 1000);
        poolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(poolConfig, host, port, 20 * 1000);
    }

   public Jedis getJedis()
    {
        Jedis jedis = jedisPool.getResource();
        return jedis;

    }
}

```
配置spring的配置文件
```java
package com.deyuan.gmall.config;

import com.deyuan.gmall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfig {
    @Value("${spring.redis.host:disable}")
    private String host;
    @Value("${spring.redis.port:0}")
    private int port;
    @Value("${Spring.data.database:0}")
    private int database;
    @Bean
    public RedisUtil getRedisUtil()
    {
        if (host.equals("disable"))
        {
            return null;
        }
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initPool(host,port,database);
        return redisUtil;
    }

}

```
就可以直接使用
@Autowired注解直接完成引用
redies三个现象
缓存穿透： 一个肯定不存在的值通过缓存发现没有这个值就会访问数据数据库使数据库压力过大崩掉
解决方法：
1.访问数据库值为空直接给该key设置一个固定值并设置有效时间
缓存击穿：在一个热点连接上，某一时刻缓存过期，由于访问人数太多直接访问数据库导致数据库崩掉
解决方法：
1.使用redis自己的set  NX方法 ：通过set "sku:2:lock" px 10000 nx
当前命令表示10s内不能设置sku:2:lock的值，其他用户采用自旋的方式等待前一个用户访问完才可访问
java实现：jedis.set("sku:2:lock","1","px","nx",10000);自旋一定使用return 返回方法不然会产生新的线程，方法失效;
2.采用redisson的JUC锁的机制;与java的锁机制一样
雪崩：当多个访问节点同一时间失效导致大量的连接跑到数据库导致数据库崩坏
解决方法：使用随机时间设置失效时间

## elasticserch

### 搭建

版本6.3.1：

java的所有环境

下载elasticsearch的安装包直接解压就可以

通过root用户创建一个新用户来启动elasticsearch

报错:权限问题直接使用chrom 777 -R elasticsearch的根目录

这是一种错误的方式正确的是一个文件一个文件的授权而不是集体授权

### 配置：

1.提示内存不足报错：

修改jvm.option的内存大小

2.外网不可以访问：

修改elasticsearch.yml文件修改host地址，并开放端口

3.出现一些文件数太少，各种东西太低：

修改/etc/security/limits这个文件里边的4个属性直接一定卸载#end file..前面

还需/etc/sysctl.conf文件添加两句

vm.nr_hugepages = 256
vm.max_map_count = 655360

基本搞定：一定使用非root用户登录

### ES项目开发分析
1.内容分析
2.表结构设计
3.与springboot整合
4.内容初始化
5.引入前端开发代码
#### 页面展示内容：
商品ID：
商品名称：(展示/查询)
商品描述: (展示/查询)
商品三级分类ID：(查询)
商品图片：(展示)
商品价格：(展示/查询)
商品平台属性列表：(查询)
热度值：(查询)
主键：


#### ES-->Mapping设计
```json
{
 "mappings":{
    "skuInfo":{
    "properties":{
      "Id":{
        "type":"long",
        "index":false
      },
      "price":{
        "type":"double"
      },
      "skuName":{
       "type":"text", 
      "analyzer":"ik_max_word"
      },
      "skuDesc":{
        "type":"text",
        "analyzer":"ik_smart"
      },
      "catalog3Id":{
        "type":"long",
        "index":false
      },
      "skuDefaultImg":{
        "type":"keyword",
        "index":false
      },
      "skuAttrValueList":{
        "properties":{
          "valueId":{
            "type":"long"
          }
        }
      }
    }
  }
 }
}
```
查看建立的结构
http://120.27.240.124:9200/gmall/_mapping
```json
{
    "gmall": {
        "mappings": {
            "skuInfo": {
                "properties": {
                    "Id": {
                        "type": "long",
                        "index": false
                    },
                    "catalog3Id": {
                        "type": "long",
                        "index": false
                    },
                    "price": {
                        "type": "double"
                    },
                    "skuAttrValueList": {
                        "properties": {
                            "valueId": {
                                "type": "long"
                            }
                        }
                    },
                    "skuDefaultImg": {
                        "type": "keyword",
                        "index": false
                    },
                    "skuDesc": {
                        "type": "text",
                        "analyzer": "ik_smart"
                    },
                    "skuName": {
                        "type": "text",
                        "analyzer": "ik_max_word"
                    }
                }
            }
        }
    }
}
```
整合springboot
引入依赖：
```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-elasticsearch</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.searchbox/jest -->
		<dependency>
			<groupId>io.searchbox</groupId>
			<artifactId>jest</artifactId>
			<version>5.3.3</version>
		</dependency>


		<!-- https://mvnrepository.com/artifact/net.java.dev.jna/jna -->
		<dependency>
			<groupId>net.java.dev.jna</groupId>
			<artifactId>jna</artifactId>
			<version>4.5.1</version>
		</dependency>
```
使用spring的自动配置就可以完成自动配置
配置jest就可以使用了
只需要在使用的地方加上spring.elasticsearch.jest.uris=http://120.27.240.124:9200
写入数据操作：
```java
 @Reference
    PmsSkuService pmsSkuService;

    @Autowired
    JestClient jestClient;
    @Test
    public void contextLoads() throws IOException {

        List<PmsSkuInfo> pmsSkuInfoList =new ArrayList<>();
        pmsSkuInfoList=pmsSkuService.getAllSku();

        List<PmsSearchSkuInfo> pmsSearchSkuInfos=new ArrayList<>();

        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList) {
            PmsSearchSkuInfo pmsSearchSkuInfo = new PmsSearchSkuInfo();
            BeanUtils.copyProperties(pmsSkuInfo,pmsSearchSkuInfo);
            pmsSearchSkuInfos.add(pmsSearchSkuInfo);
        }
        for (PmsSearchSkuInfo searchSkuInfo : pmsSearchSkuInfos) {
            Index put=new Index.Builder(searchSkuInfo).index("gmall").type("skuInfo").id(searchSkuInfo.getId()+"").build();
            jestClient.execute(put);
        }
    }
```
查询数据：
使用DSL查询数据
```json
{
  "query": { 
    "bool": { 
      "must": [
        { "match": { "title":   "Search"        }},
        { "match": { "content": "Elasticsearch" }}
      ],
      "filter": [ 
        { "term":  { "status": "published" }},
        { "range": { "publish_date": { "gte": "2015-01-01" }}}
      ]
    }
  }
}
// 针对自己内容查询语句
{
"query":{
  "bool":{
    "must":[
      {"match":{"skuName":"小米"}}
    ],
      "filter":[
        {"term":{"skuAttrValueList.valueId":"74"}}
      ]
  }
}

}

```








