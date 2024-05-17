package com.example.service;


import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class UserService {

    @Resource
    private UserMapper userMapper;

    public void add(User user) {
//        数据库检查新增用户是否已经存在->mapper
        User dbuser = userMapper.selectByUserName(user.getUsername());
        if(ObjectUtil.isNotEmpty(dbuser)){
//            如果不为空（用户存在）
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
//                   调用目录common-enums-ResultCodeEnum里面的错误码
        }
//        初始化页面上没有填写的信息(密码、名字、会员）
        if(ObjectUtil.isEmpty(user.getPassword())){
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if(ObjectUtil.isEmpty(user.getName())){
            user.setUsername(user.getUsername());
        }

        user.setRole(RoleEnum.USER.name());

//        调用UserMapper
        userMapper.insert(user);
        
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    /**
     * 修改同时修改studentflag
     */
    public void updateByIdWithSF(User user) {
        if (user.getCollegeId() != null || user.getMajorId() != null || user.getClassId() != null) {
            user.setStudentflag("1");
        }
        userMapper.updateById(user);
    }
    /**
     * 修改studentflag为0
     */
    public void updateStudentFlagTo0(User user) {
        user.setStudentflag("0"); // 设置studentflag为0
        userMapper.updateById(user); // 调用mapper更新用户
    }



    /**
     * 根据ID查询
     */
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    /**
     * 分页查询
     */
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }
    /**
     * 分页查询关联
     */
    public PageInfo<User> selectPageRelate(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAllRelate(user);
        return PageInfo.of(list);
    }
    /**
     * 根据studentflag分页查询
     */
    public PageInfo<User> selectPageByStudentFlag(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectByStudentFlag(user);
        return PageInfo.of(list);
    }


    /**
     * 登录
     */
    public Account login(Account account) {
        Account dbUser = userMapper.selectByUserName(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    /**
     * 注册
     */
    public void register(Account account) {
        User user = new User();
        BeanUtils.copyProperties(account, user);
        add(user);
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        User dbUser = userMapper.selectByUserName(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbUser.setPassword(account.getNewPassword());
        userMapper.updateById(dbUser);
    }
}
