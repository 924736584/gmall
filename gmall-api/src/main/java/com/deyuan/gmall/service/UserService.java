package com.deyuan.gmall.service;

import com.deyuan.gmall.bean.UmsMember;
import com.deyuan.gmall.bean.UmsMemberReceiveAddress;

import java.io.Serializable;
import java.util.List;

public interface UserService extends Serializable {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddress(String memberId);
}
