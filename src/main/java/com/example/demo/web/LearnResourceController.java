package com.example.demo.web;

import com.example.demo.entity.LearnResource;
import com.example.demo.service.LearnService;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/learn")
public class LearnResourceController {

    @Autowired
    private LearnService learnService;


   /* @GetMapping
    public ModelAndView index() {

        List<LearnResource> learnList = new ArrayList<LearnResource>();
        LearnResource bean = new LearnResource("官方参考文档", "Spring Boot Reference Guide", "http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application");
        learnList.add(bean);
        bean = new LearnResource("官方SpriongBoot例子", "官方SpriongBoot例子", "https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples");
        learnList.add(bean);
        bean = new LearnResource("龙国学院", "Spring Boot 教程系列学习", "http://www.roncoo.com/article/detail/125488");
        learnList.add(bean);
        bean = new LearnResource("嘟嘟MD独立博客", "Spring Boot干货系列 ", "http://tengj.top/");
        learnList.add(bean);
        bean = new LearnResource("后端编程嘟", "Spring Boot教程和视频 ", "http://www.toutiao.com/m1559096720023553/");
        learnList.add(bean);
        bean = new LearnResource("程序猿DD", "Spring Boot系列", "http://www.roncoo.com/article/detail/125488");
        learnList.add(bean);
        bean = new LearnResource("纯洁的微笑", "Sping Boot系列文章", "http://www.ityouknow.com/spring-boot");
        learnList.add(bean);
        bean = new LearnResource("CSDN——小当博客专栏", "Sping Boot学习", "http://blog.csdn.net/column/details/spring-boot.html");
        learnList.add(bean);
        bean = new LearnResource("梁桂钊的博客", "Spring Boot 揭秘与实战", "http://blog.csdn.net/column/details/spring-boot.html");
        learnList.add(bean);
        bean = new LearnResource("林祥纤博客系列", "从零开始学Spring Boot ", "http://412887952-qq-com.iteye.com/category/356333");
        learnList.add(bean);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }*/

   @PostMapping("/queryLearnList")
   @ResponseBody
   public HashMap<String, Object> queryLearnList (HttpServletRequest request){
       Map<String, Object> params = new HashMap<>();
       params.put("page",request.getParameter("page"));
       params.put("rows",request.getParameter("rows"));
       params.put("author",request.getParameter("author"));
       params.put("title",request.getParameter("title"));
       List<LearnResource> list = learnService.queryLearnResourceList(params);
       PageInfo<LearnResource> pageInfo = new PageInfo<>(list);

       // 返回结果
       HashMap<String, Object> map = new HashMap<>();
       map.put("rows", list);
       map.put("total", pageInfo.getPages());//总页数
       map.put("records",pageInfo.getTotal());//查询出的总记录数
       return map;
   }


   // add  update

    @ResponseBody
    @PostMapping("/add")
    public HashMap add(HttpServletRequest request){
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        HashMap<String, Object> map = new HashMap<>();

        if(StringUtils.isBlank(author)){
            map.put("message","作者不能为空!");
            map.put("flag",false);
            return map;
        }
        if(StringUtils.isBlank(title)){
            map.put("message","教程名称不能为空!");
            map.put("flag",false);
            return map;
        }
        if(StringUtils.isBlank(url)){
            map.put("message","地址不能为空!");
            map.put("flag",false);
            return map;
        }

        LearnResource resource = new LearnResource(author,title,url);
        learnService.add(resource);
        map.put("message","教程信息添加成功!");
        map.put("flag",true);
        return map;

    }


    /**
     * 修改教程
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public HashMap updateLearn(HttpServletRequest request , HttpServletResponse response){
        HashMap result=new HashMap<String,Object>();
        String id = request.getParameter("id");
        LearnResource learnResouce=learnService.queryLearnResourceById(Long.valueOf(id));
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if(StringUtils.isEmpty(author)){
            result.put("message","作者不能为空!");
            result.put("flag",false);
            return result;
        }
        if(StringUtils.isEmpty(title)){
            result.put("message","教程名称不能为空!");
            result.put("flag",false);
            return result;
        }
        if(StringUtils.isEmpty(url)){
            result.put("message","地址不能为空!");
            result.put("flag",false);
            return result;
        }
        learnResouce.setAuthor(author);
        learnResouce.setTitle(title);
        learnResouce.setUrl(url);
        int index=learnService.update(learnResouce);
        System.out.println("修改结果="+index);
        if(index>0){
            result.put("message","教程信息修改成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息修改失败!");
            result.put("flag",false);
        }
        return result;
    }
    /**
     * 删除教程
     * @param request
     * @param response
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public HashMap delete(HttpServletRequest request ,HttpServletResponse response){
        String ids = request.getParameter("ids");
        System.out.println("ids==="+ids);
        HashMap result=new HashMap<String,Object>();
        //删除操作
        int index = learnService.deleteByIds(ids.split(","));
        if(index>0){
            result.put("message","教程信息删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息删除失败!");
            result.put("flag",false);
        }
        return result;
    }











}
