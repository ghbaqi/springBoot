package com.example.demo.redis;

import com.example.demo.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 将登陆后的 session 信息存到 Redis 中，从而实现分布式系统中的 session 共享
 */
@RestController
public class HelloController {

    /**
     * 将登陆后的 session 信息存到 redis 中
     *
     * @return
     */
    @GetMapping("/redisSession")
    public String redisSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User("root", "root");
            session.setAttribute("user", user);
        }
        return session.getId();
    }
}
