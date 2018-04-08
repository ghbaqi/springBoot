package com.example.demo.web;

import com.example.demo.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@Controller
public class UserController {

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(HttpServletRequest request,String userName,String  password) {
        HashMap<String, Object> map = new HashMap<>();
        if ("root".equals(userName) && "root".equals(password)) {
            map.put("result", "1");
            User user = new User(userName, password);
            request.getSession().setAttribute("user",user);  // 此时的 session 已经存到 Redis 中了 ！！！
        } else {
            map.put("result", "0");
        }
        return map;
    }
}
