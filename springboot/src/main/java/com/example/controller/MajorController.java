package com.example.controller;

import com.example.common.Result;
import com.example.entity.Major;
import com.example.service.MajorService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专业信息表前端操作接口
 **/
@RestController
@RequestMapping("/major")
public class MajorController {

    @Resource
    private MajorService majorService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Major major) {
        majorService.add(major);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        majorService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        majorService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Major major) {
        majorService.updateById(major);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Major major = majorService.selectById(id);
        return Result.success(major);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Major major ) {
        List<Major> list = majorService.selectAll(major);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Major major,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Major> page = majorService.selectPage(major, pageNum, pageSize);
        return Result.success(page);
    }

}