package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.RecommendEnum;
import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date; // 添加这一行导入语句



@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Autowired CourseMapper courseMapper1;
    /**
     * 新增
     */
    public void add(Course course) {
        if (ObjectUtil.isNotEmpty(course.getRecommend()) && RecommendEnum.YES.getStatus().equals(course.getRecommend())) {
            // 获取当前推荐课程数量
            int recommendCount = courseMapper1.countRecommend();
            // 如果当前推荐课程数量大于等于3，则取消最早设置的推荐课程
            if (recommendCount >= 3) {
                courseMapper1.cancelEarliestRecommend();
            }
            // 设置推荐时间为当前时间
            course.setRecommendTime(new Date());
        }
        course.setAddTime(DateUtil.format(new Date(),"yyyy-MM-dd"));
        // 在这里设置recommendTime之后再插入数据
        courseMapper.insert(course);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            courseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Course course) {
        if (ObjectUtil.isNotEmpty(course.getRecommend()) && RecommendEnum.YES.getStatus().equals(course.getRecommend())) {
            // 获取当前推荐课程数量
            int recommendCount = courseMapper1.countRecommend();
            // 如果当前推荐课程数量大于等于3，则取消最早设置的推荐课程
            if (recommendCount >= 3) {
                courseMapper1.cancelEarliestRecommend();
            }
            // 设置推荐时间为当前时间
            course.setRecommendTime(new Date());
        }
        // 在这里设置recommendTime之后再更新数据
        courseMapper.updateById(course);
    }

    /**
     * 根据ID查询
     */
    public Course selectById(Integer id) {
        return courseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Course> selectAll(Course course) {
        return courseMapper.selectAll(course);
    }

    /**
     * 查询8个放在首页
     */
    public List<Course> selectFresh8(){return courseMapper.selectFresh8();}

    /**
     * 查询所有推荐课程
     */
    public List<Course> getRecommend(){
        return courseMapper.getRecommend();

    }
    /**
     * 根据栏目名称分页查询并排序
     */
    public PageInfo<Course> selectByChannelName(Course course, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseMapper.selectByChannelName(course);
        return PageInfo.of(list);
    }
    /**
     * 获取所有独特的栏目名称
     */
    public List<String> selectDistinctChannels() {
        return courseMapper.selectDistinctChannels();
    }
    /**
     * 标签页重新查询
     */
    public PageInfo<Course> selectByChannel(String channel, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = channel.equals("all") ? courseMapper.selectAll(new Course()) : courseMapper.selectByChannel(channel);
        return PageInfo.of(list);
    }
    /**
     * 栏目推荐设置
     */
    public void updateChannelRecommend(List<String> channels, Boolean channelRecommend) {
        for (String channel : channels) {
            courseMapper.updateChannelRecommend(channel, channelRecommend);
        }
    }
    /**
     * 获取已经推荐的栏目
     */
    public List<String> getRecommendedChannels() {
        return courseMapper.getRecommendedChannels();
    }
    /**
     * 获取推荐的栏目及其最新的课程
     */
    public List<Course> getRecommendedChannelsWithLatestCourse() {
        return courseMapper.getRecommendedChannelsWithLatestCourse();
    }




//    public Course getRecommend(String type) {
//        return courseMapper.getRecommend(type);
//    }

    public List<Course> selectTop8(String type) {
        return courseMapper.selectTop8(type);
    }
    /**
     * 分页查询
     */
    public PageInfo<Course> selectPage(Course course, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseMapper.selectAll(course);
        return PageInfo.of(list);
    }

}