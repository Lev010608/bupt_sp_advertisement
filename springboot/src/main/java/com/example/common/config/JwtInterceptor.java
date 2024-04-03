package com.example.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从http请求的header中获取token
        String token = request.getHeader(Constants.TOKEN);
        if (ObjectUtil.isEmpty(token)) {
            // 如果没拿到，从参数里再拿一次
            token = request.getParameter(Constants.TOKEN);
        }
        // 2. 开始执行认证
        if (ObjectUtil.isEmpty(token)) {
            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }
        //所有用户基类，保存账号、密码、头像
        Account account = null;
        try {
            // 解析token获取存储的数据
            String userRole = JWT.decode(token).getAudience().get(0);
            String userId = userRole.split("-")[0];
            String role = userRole.split("-")[1];
            // 根据userId查询数据库
            if (RoleEnum.ADMIN.name().equals(role)) {
                account = adminService.selectById(Integer.valueOf(userId));
            }
            if (RoleEnum.USER.name().equals(role)) {
                account = userService.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        if (ObjectUtil.isNull(account)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        return true;
    }
}



    //修改后的preHandle、添加的isPublic
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        // 1. 从http请求的header中获取token
//        String token = request.getHeader(Constants.TOKEN);
//
//        // 公开路径直接放行，例如访客访问
//        if (isPublicPath(request.getRequestURI())) {
//            return true;
//        }
//
//
//        if (ObjectUtil.isEmpty(token)) {
//            // 如果没拿到，从参数里再拿一次
//            token = request.getParameter(Constants.TOKEN);
//        }
//        // 需要认证的路径处理
//        // 2. 开始执行认证
//        if (ObjectUtil.isEmpty(token)) {
//            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
//        }
//
//        //所有用户基类，保存账号、密码、头像
//        Account account = null;
//        try {
//            // 解析token获取存储的数据
//            String userRole = JWT.decode(token).getAudience().get(0);
//            String userId = userRole.split("-")[0];
//            String role = userRole.split("-")[1];
//
//            // 根据userId查询数据库
//            if (RoleEnum.ADMIN.name().equals(role)) {
//                account = adminService.selectById(Integer.valueOf(userId));
//            }else if (RoleEnum.USER.name().equals(role)) {
//                account = userService.selectById(Integer.valueOf(userId));
//            }
//
//            if (ObjectUtil.isNotNull(account)) {
//                // 用户密码加签验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
//                jwtVerifier.verify(token); // 验证token
//            }else {
//                throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
//            }
//        } catch (Exception e) {
//            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
//        }
//        return true;
//    }
//
//    private boolean isPublicPath(String path) {
//        // 定义哪些路径是公开的
//        return "/front/home".equals(path);
//    }
//}