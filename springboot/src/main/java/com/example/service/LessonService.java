package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.RecommendEnum;
import com.example.entity.Lesson;
import com.example.mapper.LessonMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class LessonService {

    @Resource
    private LessonMapper lessonMapper;



    /**
     * 新增
     */
    public void add(Lesson lesson) {
        lessonMapper.insert(lesson);
        if (lesson.getClassIds() != null) {
            for (Integer classId : lesson.getClassIds()) {
                lessonMapper.insertLessonClass(lesson.getId(), classId);
            }
        }
    }

    /**
     * 为课件添加班级ID
     */
    public void addClassToLesson(Integer lessonId, Integer classId) {
        lessonMapper.insertLessonClass(lessonId, classId);
    }

    /**
     * 教师新增课件时关联班级
     */
    public void addLessonInClass(Lesson lesson, Integer lessonId, Integer classId) {
        lessonMapper.insert(lesson);
        lessonMapper.insertLessonClass(lessonId, classId);
    }




    /**
     * 删除
     */
    public void deleteById(Integer id) {
        lessonMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            lessonMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Lesson lesson) {
        lessonMapper.updateById(lesson);
        lessonMapper.deleteLessonClassByLessonId(lesson.getId());
        if (lesson.getClassIds() != null) {
            for (Integer classId : lesson.getClassIds()) {
                lessonMapper.insertLessonClass(lesson.getId(), classId);
            }
        }
    }

    /**
     * 根据ID查询
     */
    public Lesson selectById(Integer id) {
        return lessonMapper.selectById(id);
    }

    /**
     * 根据班级ID查询课件
     */
    public List<Lesson> selectByClassId(Integer classId) {
        return lessonMapper.selectByClassId(classId);
    }

    /**
     * 查询所有
     */
    public List<Lesson> selectAll(Lesson lesson) {
        return lessonMapper.selectAll(lesson);
    }
    
    /**
     * 分页查询
     */
    public PageInfo<Lesson> selectPage(Lesson lesson, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> list = lessonMapper.selectAll(lesson);
        return PageInfo.of(list);
    }
    
    /**
     * 根据课件类型分页查询
     */
    public PageInfo<Lesson> selectByType(String type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> list = lessonMapper.selectByType(type);
        return PageInfo.of(list);
    }


}