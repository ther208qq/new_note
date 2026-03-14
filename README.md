# new_note 个人笔记系统

## 项目简介
基于spring boot+MyBatis实现的轻量级笔记管理工具

## 技术栈
* 后端：Java 21 / Spring Boot 3.x
* 数据库：MySQL 8.0
* 持久层：MyBatis-Plus

## 当前进度
- [x] Spring Boot 基础环境搭建
- [x] 集成 MyBatis 实现笔记的增加和查找,补充了删除和更新笔记的功能
- [ ] 待办：实现一次删除多个笔记，更新功能升级为对原笔记选择全部更新或者追加或部分更新。
- [ ] 实现前端功能


## 技术笔记
* maven：标准目录结构、依赖管理（pom.xml文件）、自动化构建（maven clean、mvn compile、mvn test、mvn package）、项目生命周期管理
* spring boot框架：内置Tomcat、自动配置、提供spring-boot-starter-web（Web 开发必备的工具）、处理 URL 请求、对象管家（容器和注入 实现解耦（业务对象之间的解耦））、把对象转换成json格式，返回给浏览器
* mybatis：消除繁琐的 JDBC 代码（手动加载驱动、手动建立 Connection 连接、手动写 SQL 语句、手动从返回的 ResultSet 里一行一行拿数据，然后 set 到你的 Java 对象（Note 对象）里 减轻了这些负担）、让 SQL 和 Java 代码解耦（Java代码和sql语句的解耦）、实现“对象”与“数据库表”的映射
