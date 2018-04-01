package com.example.demo.dao;

import com.example.demo.mapper.MmallUserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Autowired
    private MmallUserMapper mapper;

    @Test
    public void test(){
        System.out.println("test");
        System.out.println("mapper = "+mapper);
        System.out.println("user = "+mapper.selectByPrimaryKey(1));

    }
}
