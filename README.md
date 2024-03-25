# BUPT南太孔院宣传门户开发

> BUPT_sp advertisement



## 项目进度

- 3.13
  - 完成脚手架搭建与上传
- 3.15
  - 完成用户信息管理模块
  - 完成个人信息管理模块
- 3.16
  - 完成信息管理与富文本编辑模块上传
  - 修改前台部分ui
- 3.25
  - 完成首页前台静态部署



## IDEA Clone事项

1. maven配置

   克隆后请检查pom.xml,如果为此图标

![image-20240313232451477](README.assets/image-20240313232451477.png)

则表明maven配置已完成，进行vue配置



2. vue配置

   检查是否有vue/node_modules

   - 如果没有，在终端执行

     ```
     cd vue
     npm i
     ```

     安装Vue

   - 如果有，进行启动配置

     > 编辑配置->  +  ->  npm  ->  package.jason(P)：选择jason  ->  脚本(T)选择：serve

   - 启动
