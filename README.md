# 开发文档



## 项目结构

### manager

#### springboot[后端目录]

- src-main

  - java

    - com.example

    - common

      - config

        - CorsConfig.java

          > 后端跨域配置（允许前端访问接口）

        - JwtInterceptor.java

          > 拦截器（拦截请求，做过滤）

        - WebConfig.java

          > 配置Jwt拦截器，通过其中一个配置类生气

      - enums

        > 枚举，定义编码/信息

        -  ResultCodeEnum.java

          > 定义状态码的枚举类

        - RoleEnum.java

          > 角色枚举类

      -  Constants.java

        > 常量

      - Result.java

        > 接口返回的通用返回类，包装controller返回的数据

    - controller✨

      > mybatis架构返回数据两种方式：
      >
      > 1. 注解
      >
      > 2. xml
      >
      >    controller->service->mapper

    - entity

      - Account.java

        > 所有账户基类

      - Admin.java

    - exception

      - GlobalExceptionHandler.java

        > 全局异常处理

    - mapper

    - service

    - utils

  - resources

    - mapper
    - application.yml

- pom.xml



### vue[前端目录]

- public

  > 工程入口

- src

  - assets

    > 静态文件

  - cmoponents

    > 组件，封装验证码等

  - router

    > 路由（跳转地址栏变化）

  - utils

    - request.js

      > axios封装（看基础课）

  - views

    > 前后台页面

    - front
    - manager

  - App.vue

