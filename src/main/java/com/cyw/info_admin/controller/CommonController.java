package com.cyw.info_admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sys")
public class CommonController {

    /**
     * 跳转到系统公共的错误页面
     * @return
     */
    @GetMapping("/error")
    public ModelAndView commonErrorView(){
        return new ModelAndView("error");
    }
}
