package com.deyuan.gmall.manage.service.imp;

import com.alibaba.dubbo.config.annotation.Service;

import com.deyuan.gmall.bean.PmsBaseSaleAttr;
import com.deyuan.gmall.bean.PmsProductInfo;

import com.deyuan.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.deyuan.gmall.manage.mapper.PmsProductInfoMapper;
import com.deyuan.gmall.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PmsProductServiceImpl implements PmsProductService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsProductSaleAttrMapper;

    @Override
    public List<PmsProductInfo> getSpuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(Long.parseLong(catalog3Id));
        List<PmsProductInfo> productInfos = pmsProductInfoMapper.select(pmsProductInfo);
        return productInfos;
    }

    @Override
    public List<PmsBaseSaleAttr> getbaseSaleAttrList() {
        List<PmsBaseSaleAttr> baseSaleAttrs = pmsProductSaleAttrMapper.selectAll();
        return baseSaleAttrs;
    }
}
