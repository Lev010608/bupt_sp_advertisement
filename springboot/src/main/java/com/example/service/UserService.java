package com.example.service;


import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.MemberEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
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
//初始设定为非会员，写一个枚举类
//        user.setMember(MemberEnum.NO.info);

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
}
