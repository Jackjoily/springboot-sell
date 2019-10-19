package com.jack.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-17 19:55
 *
 */
@Controller
@RequestMapping("/seller/admin")
public class SellerAdminController {

    @RequestMapping("/login")
    public String login(){
        return "admin_login";
    }
    @PostMapping("/dologin")
    public String dologin(String username, String password){
        if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)){
            if("admin".equals(username)&&"admin".equals(password)){
                return  "redirect:/seller/order/list";
            }
        }
     return  "admin_login";
    }


}