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
