> # BUPT南太孔院信息门户开发
>

> BUPT_sp advertisement
>
> author：**Lev**  **Dream_Autumn**





│  
├─.idea
│          
│          
├─springboot
│  │  pom.xml
│  │  
│  ├─src
│  │  └─main
│  │      ├─java
│  │      │  └─com
│  │      │      └─example
│  │      │          │  SpringbootApplication.java
│  │      │          │  
│  │      │          ├─common
│  │      │          │  │  Constants.java
│  │      │          │  │  Result.java
│  │      │          │  │  
│  │      │          │  ├─config
│  │      │          │  │      CorsConfig.java
│  │      │          │  │      JwtInterceptor.java
│  │      │          │  │      WebConfig.java
│  │      │          │  │      WebSocketConfig.java
│  │      │          │  │      WebSocketSingleServer.java
│  │      │          │  │      
│  │      │          │  └─enums
│  │      │          │          MemberEnum.java
│  │      │          │          RecommendEnum.java
│  │      │          │          ResultCodeEnum.java
│  │      │          │          RoleEnum.java
│  │      │          │          
│  │      │          ├─controller
│  │      │          │      AdminController.java
│  │      │          │      ClassesController.java
│  │      │          │      CollegeController.java
│  │      │          │      CourseController.java
│  │      │          │      FileController.java
│  │      │          │      ImSingleController.java
│  │      │          │      InformationController.java
│  │      │          │      LessonController.java
│  │      │          │      MajorController.java
│  │      │          │      NoticeController.java
│  │      │          │      TeacherController.java
│  │      │          │      UserController.java
│  │      │          │      WebController.java
│  │      │          │      
│  │      │          ├─entity
│  │      │          │      Account.java
│  │      │          │      Admin.java
│  │      │          │      Classes.java
│  │      │          │      College.java
│  │      │          │      Course.java
│  │      │          │      ImSingle.java
│  │      │          │      Information.java
│  │      │          │      Lesson.java
│  │      │          │      Likes.java
│  │      │          │      Major.java
│  │      │          │      Notice.java
│  │      │          │      Teacher.java
│  │      │          │      User.java
│  │      │          │      
│  │      │          ├─exception
│  │      │          │      CustomException.java
│  │      │          │      GlobalExceptionHandler.java
│  │      │          │      
│  │      │          ├─mapper
│  │      │          │      AdminMapper.java
│  │      │          │      ClassesMapper.java
│  │      │          │      CollegeMapper.java
│  │      │          │      CourseMapper.java
│  │      │          │      ImSingleMapper.java
│  │      │          │      InformationMapper.java
│  │      │          │      LessonMapper.java
│  │      │          │      LikesMapper.java
│  │      │          │      MajorMapper.java
│  │      │          │      NoticeMapper.java
│  │      │          │      TeacherMapper.java
│  │      │          │      UserMapper.java
│  │      │          │      
│  │      │          ├─service
│  │      │          │      AdminService.java
│  │      │          │      ClassesService.java
│  │      │          │      CollegeService.java
│  │      │          │      CourseService.java
│  │      │          │      ImSingleService.java
│         │          │      InformationService.java
│         │          │      LessonService.java
│         │          │      MajorService.java
│         │          │      NoticeService.java
│         │          │      TeacherService.java
│         │          │      UserService.java
│         │          │      
│         │          └─utils
│         │                  TokenUtils.java
│         │                  
│         └─resources
│             │  application.yml
│             │  
│             └─mapper
│                     AdminMapper.xml
│                     ClassesMapper.xml
│                     CollegeMapper.xml
│                     CourseMapper.xml
│                     InformationMapper.xml
│                     LessonMapper.xml
│                      MajorMapper.xml
│                      NoticeMapper.xml
│                      TeacherMapper.xml
│                      UserMapper.xml
│                     
│                          
└─vue
    │  .env.development
    │  .env.production
    │  babel.config.js
    │  jsconfig.json
    │  package-lock.json
    │  package.json
    │  vue.config.js
    │  
    │                  
    ├─public
    │      index.html
    │      
    └─src
        │  App.vue
        │  main.js
        │  
        ├─assets
        │  │  emoji.js
        │  │  
        │  ├─css
        │  │  │  chat.css
        │  │  │  course.css
        │  │  │  detail.css
        │  │  │  front.css
        │  │  │  global.css
        │  │  │  home.css
        │  │  │  information.css
        │  │  │  lesson.css
        │  │  │  manager.css
        │  │  │  
        │  │  └─theme
        │  │      │  index.css
        │  │      │  
        │  │      └─fonts
        │  │              element-icons.ttf
        │  │              element-icons.woff
        │  │              
        │  ├─fonts
        │  │      
        │  ├─icons
        │  │      
        │  └─imgs
        │              
        ├─components
        ├─router
        │      index.js
        │      
        ├─utils
        │      request.js
        │      
        └─views
            │  404.vue
            │  Chat.vue
            │  Front.vue
            │  Login.vue
            │  Manager.vue
            │  Register.vue
            │  
            ├─front
            │      Channel.vue
            │      Course.vue
            │      Detail.vue
            │      Home.vue
            │      Information.vue
            │      InformationDetail.vue
            │      Lesson.vue
            │      LessonDetail.vue
            │      Person.vue
            │      Release.vue
            │      
            └─manager
                    403.vue
                    Admin.vue
                    AdminPerson.vue
                    Classes.vue
                    ClassesTAdmin.vue
                    College.vue
                    Course.vue
                    Home.vue
                    Information.vue
                    LessonAdmin.vue
                    LessonTAdmin.vue
                    Major.vue
                    Notice.vue
                    Password.vue
                    Student.vue
                    Teacher.vue
                    User.vue
                    





## 项目结构

│  
├─.idea
│          
│          
├─springboot
│  │  pom.xml
│  │  
│  ├─src
│  │  └─main
│  │      ├─java
│  │      │  └─com
│  │      │      └─example
│  │      │          │  SpringbootApplication.java
│  │      │          │  
│  │      │          ├─common
│  │      │          │  │  Constants.java `常量`
│  │      │          │  │  Result.java `接口返回的通用返回类，包装controller返回的数据`
│  │      │          │  │  
│  │      │          │  ├─config
│  │      │          │  │      CorsConfig.java `后端跨域配置（允许前端访问接口）`
│  │      │          │  │      JwtInterceptor.java `拦截器（拦截请求，过滤）`
│  │      │          │  │      WebConfig.java `配置Jwt拦截器`
│  │      │          │  │      WebSocketConfig.java `WebSocket配置`
│  │      │          │  │      WebSocketSingleServer.java `WebSocket服务器类`
│  │      │          │  │      
│  │      │          │  └─enums `枚举，定义编码/信息`
│  │      │          │          ResultCodeEnum.java `定义状态码的枚举类`
│  │      │          │          RoleEnum.java `角色枚举类`
│  │      │          │          
│  │      │          ├─controller `定义接口`
│  │      │          │      AdminController.java
│  │      │          │      ClassesController.java
│  │      │          │      CollegeController.java
│  │      │          │      CourseController.java
│  │      │          │      FileController.java
│  │      │          │      ImSingleController.java
│  │      │          │      InformationController.java
│  │      │          │      LessonController.java
│  │      │          │      MajorController.java
│  │      │          │      NoticeController.java
│  │      │          │      TeacherController.java
│  │      │          │      UserController.java
│  │      │          │      WebController.java
│  │      │          │      
│  │      │          ├─entity `实体`
│  │      │          │      Account.java
│  │      │          │      Admin.java
│  │      │          │      Classes.java
│  │      │          │      College.java
│  │      │          │      Course.java
│  │      │          │      ImSingle.java
│  │      │          │      Information.java
│  │      │          │      Lesson.java
│  │      │          │      Likes.java
│  │      │          │      Major.java
│  │      │          │      Notice.java
│  │      │          │      Teacher.java
│  │      │          │      User.java
│  │      │          │      
│  │      │          ├─exception
│  │      │          │      CustomException.java
│  │      │          │      GlobalExceptionHandler.java `全局异常处理`
│  │      │          │      
│  │      │          ├─mapper
│  │      │          │      AdminMapper.java
│  │      │          │      ClassesMapper.java
│  │      │          │      CollegeMapper.java
│  │      │          │      CourseMapper.java
│  │      │          │      ImSingleMapper.java
│  │      │          │      InformationMapper.java
│  │      │          │      LessonMapper.java
│  │      │          │      LikesMapper.java
│  │      │          │      MajorMapper.java
│  │      │          │      NoticeMapper.java
│  │      │          │      TeacherMapper.java
│  │      │          │      UserMapper.java
│  │      │          │      
│  │      │          ├─service
│  │      │          │      AdminService.java
│  │      │          │      ClassesService.java
│  │      │          │      CollegeService.java
│  │      │          │      CourseService.java
│  │      │          │      ImSingleService.java
│         │          │      InformationService.java
│         │          │      LessonService.java
│         │          │      MajorService.java
│         │          │      NoticeService.java
│         │          │      TeacherService.java
│         │          │      UserService.java
│         │          │      
│         │          └─utils
│         │                  TokenUtils.java
│         │                  
│         └─resources
│             │  application.yml
│             │  
│             └─mapper
│                     AdminMapper.xml
│                     ClassesMapper.xml
│                     CollegeMapper.xml
│                     CourseMapper.xml
│                     InformationMapper.xml
│                     LessonMapper.xml
│                      MajorMapper.xml
│                      NoticeMapper.xml
│                      TeacherMapper.xml
│                      UserMapper.xml
│                     
│                          
└─vue
    │  .env.development `后台URL`
    │  .env.production
    │  babel.config.js
    │  jsconfig.json
    │  package-lock.json
    │  package.json `依赖配置`
    │  vue.config.js `Vue全局配置文件`
    │  
    │                  
    ├─public
    │      index.html
    │      
    └─src
        │  App.vue `Vue的入口`
        │  main.js `导入所有需要的文件`
        │  
        ├─assets `静态文件`
        │  │  emoji.js
        │  │  
        │  ├─css
        │  │  │  chat.css
        │  │  │  course.css
        │  │  │  detail.css
        │  │  │  front.css
        │  │  │  global.css
        │  │  │  home.css
        │  │  │  information.css
        │  │  │  lesson.css
        │  │  │  manager.css
        │  │  │  
        │  │  └─theme
        │  │      │  index.css
        │  │      │  
        │  │      └─fonts
        │  │              element-icons.ttf
        │  │              element-icons.woff
        │  │              
        │  ├─fonts
        │  │      
        │  ├─icons
        │  │      
        │  └─imgs
        │              
        ├─components
        ├─router `路由（跳转地址栏变化）`
        │      index.js
        │      
        ├─utils
        │      request.js `axios封装`
        │      
        └─views
            │  404.vue
            │  Chat.vue
            │  Front.vue
            │  Login.vue
            │  Manager.vue
            │  Register.vue
            │  
            ├─front `展示端界面`
            │      Channel.vue
            │      Course.vue
            │      Detail.vue
            │      Home.vue
            │      Information.vue
            │      InformationDetail.vue
            │      Lesson.vue
            │      LessonDetail.vue
            │      Person.vue
            │      Release.vue
            │      
            └─manager `管理端界面`
                    403.vue
                    Admin.vue
                    AdminPerson.vue
                    Classes.vue
                    ClassesTAdmin.vue
                    College.vue
                    Course.vue
                    Home.vue
                    Information.vue
                    LessonAdmin.vue
                    LessonTAdmin.vue
                    Major.vue
                    Notice.vue
                    Password.vue
                    Student.vue
                    Teacher.vue
                    User.vue



## 项目开发日志

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
  
- 3.26
  - 完成首页前台推荐课程前后端接口编写
  - 完成推荐课程的筛选规则
  - 完成推荐数量限制的后端逻辑与前端交互
  - 完成课程信息主页展示功能
  
- 3.27
  - 完成前台“全部动态”列表开发
  - 完成内容详情页的后台逻辑框架
  
- 3.28
  - 完成前台详情页接口与UI
  - 完成前台数据筛选
  - 完成动态页数据筛选
  - 完成个人发布功能
  - 完成管理员发布审核功能
  
- 4.3
  - 完成路由规则编写
  - 完成部分ui的修改
  
- 4.4
  - 全部样式美化
  - 添加了首页的部分样式
  - 添加了后台的栏目管理
  - 添加了前台栏目页的数据获取、栏目切换
  
- 4.5
  - 前台跳转详情页
  
  - 后台栏目推荐管理
    - [x] 推荐管理和新增/编辑课程产生了冲突，有bug需要修改
    
      > bug解决，重要的是在xml文件insert、update中写入相关字段。
    
  - 栏目推荐的前台展示，和跳转具体栏目页
  
  - 后台栏目推荐表单提交时的验证功能
  
- 5.9

  - 添加了教师身份

- 5.13

  - 添加了访客身份，但是具体某些按钮需要进行验证还没写
  
- 5.15

  - 添加了学生身份，有注册bug
  - 添加了聊天室UI

- 5.16

  - 修复学生身份bug
  - 添加了教务行政管理、级联查询
  
- 5.17

  - 修复前后端bug若干
  - 学生管理学院、专业、班级级联查询
    - [x] 删除学生只会把stuentflag置为0，应该连带将所有行政信息一并删除









## 项目进度

- [x] [01. 用户信息模块功能的开发](https://www.yuque.com/xiaqing-en2ii/skflxg/fqkfyggeq1a5nxxu)

- [x] [02. 用户登录注册、个人中心和修改密码](https://www.yuque.com/xiaqing-en2ii/skflxg/ep5pcvnr7grnt0eu)
  - [x] 最好加入游客身份，游客可以查看内容，用户身份可以在论坛发表内容
  - [x] 现在，用户身份登陆后进行重定位能够直接进入后台，修改数据。需要对这一点作出修改

- [x] [03. 课程信息模块功能的开发（上）](https://www.yuque.com/xiaqing-en2ii/skflxg/sav42v1esfpugrqm)

- [x] [04. 课程信息模块功能开发（下）-课程介绍富文本](https://www.yuque.com/xiaqing-en2ii/skflxg/kp5p60ps6psa8afn)

- [x] [05. 前台首页菜单和轮播图设计](https://www.yuque.com/xiaqing-en2ii/skflxg/vty8ipnv370b9xcv)

- [x] [06. 前台首页课程和资料的页面设计](https://www.yuque.com/xiaqing-en2ii/skflxg/ldtlxpozgmtmxu81)

- [x] [07. 首页课程大图推荐、课程动态渲染](https://www.yuque.com/xiaqing-en2ii/skflxg/we6nt9qboqhzxfbe)

- [x] [08. 前台全部课程功能的开发和数据渲染](https://www.yuque.com/xiaqing-en2ii/skflxg/neyq5342qyor2ic6)

- [x] [10. 前台首页课程切换渲染功能的开发](https://www.yuque.com/xiaqing-en2ii/skflxg/fi6ginii9qi9paqx)
  - [x] 现在课程管理端栏目管理和分页出现了bug
  
    > bug解决，从后端拉取数据，而不是过滤前端数据
  
  - [x] 前端所有类型展示也有bug！
  
    > bug解决，从后端拉取数据，而不是过滤前端数据
  
- [x] [12. 前台课程详情页页面开发和数据的渲染](https://www.yuque.com/xiaqing-en2ii/skflxg/wyed89cc7qfp9gr1)

- [x] [14. 前台我的资料功能的开发](https://www.yuque.com/xiaqing-en2ii/skflxg/hyw85gkaxab3xgo4)

- [x] [15. 管理后台资料审核和推荐功能的开发](https://www.yuque.com/xiaqing-en2ii/skflxg/pvabggzu2fholbly)

- [x] [16. 前台首页在线资源部分的数据渲染](https://www.yuque.com/xiaqing-en2ii/skflxg/vvn3h046kcw85gp7)

- [x] [17. 前台海量资源以及详情页功能的开发](https://www.yuque.com/xiaqing-en2ii/skflxg/bdqmlr1rqrq454zg)

- [x] [18. 前台首页跳转到详情页功能的开发](https://www.yuque.com/xiaqing-en2ii/skflxg/vcg92wp50fpw0w57)

- [ ] [27. 数据统计功能的开发](https://www.yuque.com/xiaqing-en2ii/skflxg/vhfx75nqsc26t8vf)



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

3. 富文本编辑器配置

   > 待更新



## 部署上线服务器事项

> 主要说明更新项目的上传事项

### 打包

打包后端：
maven-clean-package

拿到jar包

打包前端：

```
cd vue
npm run build
```



#### 部署

- 前端/dist直接放入原目录即可生效

- jar包放入文件夹后：

  - cd进入其所在目录

  - 输入ll查看文件权限

    ```
    ll
    ```

  - 赋予权限

    ```
    chmod +x *
    ```

    赋权后重新查看全为绿色即可

  - 关闭并重新启动jar包

    ```
    ./stop.sh
    ./start.sh
    ```

  - 查看后端是否启动成功

    ```
    tail -100f server.log
    ```

    

