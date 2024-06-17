# 开发文档



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
                   

