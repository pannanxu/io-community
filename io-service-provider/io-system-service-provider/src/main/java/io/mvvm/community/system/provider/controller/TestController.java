package io.mvvm.community.system.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Pan
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test(HttpServletRequest request) {
        return "Hello Word";
    }
}
