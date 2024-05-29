package com.example.controller;

import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Lesson;
import com.example.service.LessonService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Resource
    private LessonService lessonService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Lesson lesson) {
        lessonService.add(lesson);
        return Result.success();
    }

    /**
     * 添加课件到班级
     */
    @PostMapping("/addClass")
    public Result addClass(@RequestBody Map<String, Integer> map) {
        Integer lessonId = map.get("lessonId");
        Integer classId = map.get("classId");
        if (lessonId != null && classId != null) {
            lessonService.addClassToLesson(lessonId, classId);
            return Result.success();
        } else {
            return Result.error(ResultCodeEnum.valueOf("Invalid parameters"));
        }
    }


    /**
     * 教师新增课件时关联班级
     */
    @PostMapping("/addLessonInClass")
    public Result addLessonInClass(@RequestBody Lesson lesson) {
        lessonService.addLessonInClass(lesson);
        return Result.success();
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        lessonService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        lessonService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Lesson lesson) {
        lessonService.updateById(lesson);
        return Result.success();
    }

    /**
     * 获取课件关联的班级ID
     */
    @GetMapping("/getClassIds/{lessonId}")
    public Result getClassIdsByLessonId(@PathVariable Integer lessonId) {
        List<Integer> classIds = lessonService.getClassIdsByLessonId(lessonId);
        return Result.success(classIds);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Lesson lesson = lessonService.selectById(id);
        return Result.success(lesson);
    }

    /**
     * 根据班级ID查询课件
     */
    @GetMapping("/selectByClassId/{classId}")
    public Result selectByClassId(@PathVariable Integer classId) {
        List<Lesson> lessons = lessonService.selectByClassId(classId);
        return Result.success(lessons);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Lesson lesson ) {
        List<Lesson> list = lessonService.selectAll(lesson);
        return Result.success(list);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Lesson lesson,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Lesson> page = lessonService.selectPage(lesson, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 根据课件类型分页查询
     */
    @GetMapping("/selectByType")
    public Result selectByType(@RequestParam String type,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Lesson> page = lessonService.selectByType(type, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询校级、院级、班级课程
     */
    @GetMapping("/selectLessonsForClass")
    public Result selectLessonsForClass(@RequestParam Integer classId,
                                        @RequestParam(required = false) Integer collegeId,
                                        @RequestParam(required = false) Integer majorId) {
        List<Lesson> lessons = lessonService.getLessonsForClass(classId, collegeId, majorId);
        return Result.success(lessons);
    }

    /**
     * 根据学生所属班级查讯课件
     */
    @GetMapping("/selectPageByClassId")
    public Result selectPageByClassId(@RequestParam Integer classId,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Lesson> page = lessonService.selectPageByClassId(classId, name, pageNum, pageSize);
        return Result.success(page);
    }


    @GetMapping("/selectSchoolLevelLessons")
    public Result selectSchoolLevelLessons(@RequestParam(required = false) Integer classId,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Lesson> page = lessonService.selectSchoolLevelLessons(classId, name, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectCollegeLevelLessons")
    public Result selectCollegeLevelLessons(@RequestParam(required = false) Integer classId,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Lesson> page = lessonService.selectCollegeLevelLessons(classId, name, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectMajorLevelLessons")
    public Result selectMajorLevelLessons(@RequestParam(required = false) Integer classId,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Lesson> page = lessonService.selectMajorLevelLessons(classId, name, pageNum, pageSize);
        return Result.success(page);
    }





}