package com.deyuan.gmall.user.service;

import com.deyuan.gmall.user.bean.UmsMember;
import com.deyuan.gmall.user.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddress(String memberId);
}
