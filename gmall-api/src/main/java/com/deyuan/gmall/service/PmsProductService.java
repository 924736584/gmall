package com.deyuan.gmall.service;

import com.deyuan.gmall.bean.PmsBaseSaleAttr;
import com.deyuan.gmall.bean.PmsProductInfo;

import java.util.List;

public interface PmsProductService {
    List<PmsProductInfo> getSpuList(String catalog3Id);

    List<PmsBaseSaleAttr> getbaseSaleAttrList();
}
