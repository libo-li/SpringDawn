package com.reedl.common.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Li Libo on 2016/12/5.
 */
@Controller
public class MainController {

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) {
        return "main";
    }
}
