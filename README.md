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
### dubbo搭建
dubbo环境搭建与zookeeper安装：

1.安装tomcat，将dubbo-admin部署到tomcat中
首先需要先解压zookeeper然后启动tomcat
否者启动就会报错