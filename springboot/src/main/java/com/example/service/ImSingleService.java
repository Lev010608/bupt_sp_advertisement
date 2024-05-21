package com.example.service;

import cn.hutool.core.lang.Dict;
import com.example.entity.ImSingle;
import com.example.mapper.ImSingleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImSingleService {

    @Resource
    private ImSingleMapper imSingleMapper;

    @Transactional
    public ImSingle add(ImSingle imSingle) {
        imSingleMapper.insert(imSingle);
        return imSingle;
    }

    @Transactional
    public List<ImSingle> findByUsername(String fromUser, String toUser) {
        List<ImSingle> list = imSingleMapper.findByUsername(fromUser, toUser);
        list.forEach(x -> {
            if (x.getTouser().equals(fromUser) && x.getFromuser().equals(toUser)) {
                x.setReaded(1);
                imSingleMapper.updateByPrimaryKey(x);
            }
        });
//        System.out.println("********" + list);
        return list;
    }

    @Transactional
    public List<ImSingle> findAll() {
        List<ImSingle> list = imSingleMapper.findAll();
//        list.forEach(x -> {
//            if (x.getTouser().equals(fromUser) && x.getFromuser().equals(toUser)) {
//                x.setReaded(1);
//                imSingleMapper.updateByPrimaryKey(x);
//            }
//        });
//        System.out.println("********" + list);
        return list;
    }

    @Transactional
    public Dict findUnReadNums(String toUsername) {
        List<ImSingle> list = imSingleMapper.findByToUsername(toUsername);
        Map<String, List<ImSingle>> collect = list.stream().collect(Collectors.groupingBy(ImSingle::getFromuser));
        Dict dict = Dict.create();
        collect.forEach((k, v) -> dict.set(k, v.size()));
        return dict;
    }
}
