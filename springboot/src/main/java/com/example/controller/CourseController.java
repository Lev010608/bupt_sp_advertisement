package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        courseService.add(course);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        courseService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Course course) {
        courseService.updateById(course);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Course course = courseService.selectById(id);
        return Result.success(course);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Course course ) {
        List<Course> list = courseService.selectAll(course);
        return Result.success(list);
    }

    @GetMapping("/selectFresh8")
    public Result selectFresh8(){
        List<Course> list = courseService.selectFresh8();
        return Result.success(list);
    }

    /**
     * 查询推荐
     */
    @GetMapping("/getRecommend")
    public Result getRecommend(){
        List<Course> courses = courseService.getRecommend();
        return Result.success(courses);
    }

//    @GetMapping("/getRecommend")
//    public Result getRecommend(@RequestParam String type) {
//        Course course = courseService.getRecommend(type);
//        return Result.success(course);
//    }

    /**
     * 查询前八个数据
     */
    @GetMapping("/selectTop8")
    public Result selectTop8(@RequestParam String type) {
        List<Course> list = courseService.selectTop8(type);
        return Result.success(list);
    }
    /**
     * 根据栏目名称分页查询并排序
     */
    @GetMapping("/selectByChannelName")
    public Result selectByChannelName(Course course,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Course> page = courseService.selectByChannelName(course, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 获取所有独特的栏目名称
     */
    @GetMapping("/selectDistinctChannels")
    public Result selectDistinctChannels() {
        List<String> channels = courseService.selectDistinctChannels();
        return Result.success(channels);
    }
    /**
     * 标签页重新查询
     */
    @GetMapping("/selectByChannel")
    public Result selectByChannel(@RequestParam String channel,
                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Course> page = courseService.selectByChannel(channel, pageNum, pageSize);
        return Result.success(page);
    }
    /**
     * 栏目推荐设置
     */
    @PutMapping("/updateChannelRecommend")
    public Result updateChannelRecommend(@RequestBody Map<String, Object> params) {
        List<String> channels = (List<String>) params.get("channels");
        Boolean channelRecommend = (Boolean) params.get("channelRecommend");
        courseService.updateChannelRecommend(channels, channelRecommend);
        return Result.success();
    }
    /**
     * 获取已推荐栏目
     */
    @GetMapping("/recommendedChannels")
    public Result getRecommendedChannels() {
        List<String> recommendedChannels = courseService.getRecommendedChannels();
        return Result.success(recommendedChannels);
    }
    /**
     * 获取推荐的栏目及其最新的课程
     */
    @GetMapping("/getRecommendedChannelsWithLatestCourse")
    public Result getRecommendedChannelsWithLatestCourse() {
        List<Course> courses = courseService.getRecommendedChannelsWithLatestCourse();
        return Result.success(courses);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Course course,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Course> page = courseService.selectPage(course, pageNum, pageSize);
        return Result.success(page);
    }

}