package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.enums.RecommendEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Information;
import com.example.entity.Likes;
import com.example.mapper.LikesMapper;
import com.example.exception.CustomException;
import com.example.mapper.InformationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: 幻秋
 * @Pcakage: com.example.service.InformationService
 * @Date: 2024年03月28日 19:01
 * @Description:
 */
@Service
public class InformationService {

    @Resource
    private InformationMapper informationMapper;
    @Resource
    private LikesMapper likesMapper;

    /**
     * 新增
     */
    public void add(Information information) {
        information.setAddTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        informationMapper.insert(information);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        informationMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            informationMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Information information) {
        if (RecommendEnum.YES.getStatus().equals(information.getRecommend())) {
            // 校验一下是否超过推荐的上限
            List<Information> recommendedList = informationMapper.selectRecommended();
            if (recommendedList.size() >= 18 && !recommendedList.stream().anyMatch(info -> info.getId().equals(information.getId()))) {
                throw new CustomException(ResultCodeEnum.RECOMMEND_LIMIT_ERROR);
            }
        }
        informationMapper.updateById(information);
    }

    public List<Information> selectRecommended() {
        return informationMapper.selectRecommended();
    }


    /**
     * 根据ID查询
     */
    public Information selectById(Integer id) {
        return informationMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Information> selectAll(Information information) {
        return informationMapper.selectAll(information);
    }

    /**
     * 分页查询
     */
    public PageInfo<Information> selectPage(Information information, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Information> list = informationMapper.selectAll(information);
        return PageInfo.of(list);
    }
    /**
     * 分页查询审核通过
     */
    public PageInfo<Information> selectApproved(Integer pageNum, Integer pageSize, String name, String tag) {
        PageHelper.startPage(pageNum, pageSize);
        List<Information> list = informationMapper.selectApproved(name,tag);
        return PageInfo.of(list);
    }

    /**
     * 查询标签
     */
    public List<String> getDistinctTags() {
        return informationMapper.selectDistinctTags();
    }


    public List<Information> selectTop8() {
        return informationMapper.selectTop8();
    }

    public List<Information> selectRecommendedApproved() {
        return informationMapper.selectRecommendedApproved();
    }



}