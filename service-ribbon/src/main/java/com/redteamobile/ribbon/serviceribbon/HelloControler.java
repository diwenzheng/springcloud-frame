package com.redteamobile.ribbon.serviceribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloControler {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }

    /**
     * forward 转发，如return "forward:/hello"; 浏览器的地址栏不会变，但是有视图返回来
     * redirect 重定向，如return "redirect:/hello"; 浏览器的地址栏会变。
     * @return
     */
    @RequestMapping("/he")
    public String testRedirect(){
        return "redirect:/hello";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}


