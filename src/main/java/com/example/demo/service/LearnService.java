package com.example.demo.service;

import com.example.demo.entity.LearnResource;

import java.util.List;
import java.util.Map;

/**
 * Created by tengj on 2017/4/7.
 */

public interface LearnService {
    int add(LearnResource learnResouce);
    int update(LearnResource learnResouce);
    int deleteByIds(String[] ids);
    LearnResource queryLearnResourceById(Long learnResouce);
    List<LearnResource> queryLearnResourceList(Map<String, Object> params);
}
