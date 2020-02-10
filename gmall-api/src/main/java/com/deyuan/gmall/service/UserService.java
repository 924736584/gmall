package com.deyuan.gmall.service;

import com.deyuan.gmall.bean.UmsMember;
import com.deyuan.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddress(String memberId);
}
