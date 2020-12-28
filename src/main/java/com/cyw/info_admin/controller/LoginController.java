package com.cyw.info_admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
@Slf4j
public class LoginController {


    /**
     * 审核管理台登录页面
     * @return
     */
    @GetMapping("/")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }
}
