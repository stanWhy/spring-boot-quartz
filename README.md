# spring-boot-quartz

基于spring-boot+quartz的CRUD动态任务管理系统，适用于中小项目。


## 开发环境

JDK1.7、Maven、Eclipse

## 技术栈

SpringBoot1.5.2、thymeleaf、quartz2.3.0、iview、vue、layer、AdminLTE、bootstrap

## 启动说明
- 项目使用的数据库为MySql，选择resources/sql中的tables_mysql_innodb.sql文件初始化数据库信息。
- 在resources/application.properties文件中替换为自己的数据源。
- 运行Application main方法，启动项目。
- 访问地址：http://localhost:8080/quartz
- API接口地址：http://localhost:8080/quartz/swagger-ui.html

## 友情提示
由于工作原因，项目正在完善中（仅供参考），随时更新日志。

## 项目截图

![输入图片说明](https://gitee.com/uploads/images/2018/0331/181340_cfbf6c90_87650.png "1.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0331/181347_8b91b864_87650.png "2.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0331/181352_cfcdce10_87650.png "3.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0331/181357_e41c9cd9_87650.png "4.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0331/181403_b82f6edd_87650.png "5.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0331/181409_b5623f27_87650.png "6.png")

## 已有功能

- 任务列表
- 任务新增和修改
- 任务执行

## 待集成功能

- 系统登录以及权限管理
- 任务停止和开启
- 任务列表分页


