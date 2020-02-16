package com.deyuan.gmall.service;

import com.deyuan.gmall.bean.PmsBaseAttrInfo;
import com.deyuan.gmall.bean.PmsBaseAttrValue;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> getattrInfoList(String catalog3Id);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

}
