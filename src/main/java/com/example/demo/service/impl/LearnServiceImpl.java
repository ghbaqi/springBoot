package com.example.demo.service.impl;

import com.example.demo.entity.LearnResource;
import com.example.demo.mapper.LearnResourceMapper;
import com.example.demo.service.LearnService;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    private LearnResourceMapper mapper;

    @Override
    public int add(LearnResource learnResouce) {
        return  mapper.insert(learnResouce);
    }

    @Override
    public int update(LearnResource learnResouce) {
        return mapper.updateByPrimaryKeySelective(learnResouce);
    }

    @Override
    public int deleteByIds(String[] ids) {
        for (String id : ids) {
            mapper.deleteByPrimaryKey(Long.parseLong(id));
        }
        return ids.length;
    }

    @Override
    public LearnResource queryLearnResourceById(Long learnResouce) {
        return mapper.selectByPrimaryKey(learnResouce);
    }


    @Override
    public List<LearnResource> queryLearnResourceList(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt((String) params.get("page")), Integer.parseInt((String) params.get("rows")));
        List<LearnResource> list = mapper.queryLearnResouceList(params);
        return list;
    }
}
