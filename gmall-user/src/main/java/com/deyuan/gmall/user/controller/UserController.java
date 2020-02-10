package com.deyuan.gmall.user.controller;

import com.deyuan.gmall.bean.UmsMember;
import com.deyuan.gmall.bean.UmsMemberReceiveAddress;
import com.deyuan.gmall.user.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceimpl userService;

    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddress(@PathParam("memberId") String memberId)
    {
        List<UmsMemberReceiveAddress> UmsMemberReceiveAddress = userService.getReceiveAddress(memberId);
        return UmsMemberReceiveAddress;
    }
    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser()
    {
        List<UmsMember> umsNumbers=userService.getAllUser();
        return umsNumbers;
    }

    @RequestMapping("index")
    @ResponseBody
    public String index()
    {
        return "weCome SpringBoot";
    }
}
