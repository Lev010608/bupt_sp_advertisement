package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }
    /**
     * 修改同时调整studentflag为1
     */
    @PutMapping("/updateWithSF")
    public Result updateByIdWithSF(@RequestBody User user) {
        userService.updateByIdWithSF(user);
        return Result.success();
    }
    /**
     * 修改studentflag为0
     */
    @PutMapping("/updateSFTo0")
    public Result updateStudentFlagTo0(@RequestBody User user) {
        userService.updateStudentFlagTo0(user);
        return Result.success();
    }

    /**
     * 老师踢出学生
     */
    @PutMapping("/kickMyStudent")
    public Result kickMyStudent(@RequestBody User user) {
        userService.kickMyStudent(user);
        return Result.success();
    }



    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user ) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }
    /**
     * 分页查询关联学院专业班级
     */
    @GetMapping("/selectPageRelate")
    public Result selectPageRelate(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPageRelate(user, pageNum, pageSize);
        return Result.success(page);
    }
    /**
     * 根据studentflag分页查询
     */
    @GetMapping("/selectPageByStudentFlag")
    public Result selectPageByStudentFlag(User user,
                                          @RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPageByStudentFlag(user, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 根据班级ID查询学生
     */
    @GetMapping("/selectByClassId/{classId}")
    public Result selectByClassId(@PathVariable Integer classId) {
        List<User> list = userService.selectByClassId(classId);
        return Result.success(list);
    }




}