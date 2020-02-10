package com.deyuan.gmall.user.service.impl;

import com.deyuan.gmall.bean.UmsMember;
import com.deyuan.gmall.bean.UmsMemberReceiveAddress;
import com.deyuan.gmall.service.UserService;
import com.deyuan.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.deyuan.gmall.user.mapper.UserMapper;
import com.deyuan.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper memberReceiveAddressMapper;

    public List<UmsMember> getAllUser() {
   return userMapper.selectAll();
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddress(String memberId) {
        Example e=new Example(UmsMemberReceiveAddress.class);
        e.createCriteria().andEqualTo("memberId",memberId);
      return  memberReceiveAddressMapper.selectByExample(e);

    }
}
