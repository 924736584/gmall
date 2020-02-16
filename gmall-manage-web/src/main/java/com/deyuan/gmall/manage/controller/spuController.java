package com.deyuan.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.deyuan.gmall.bean.PmsBaseSaleAttr;
import com.deyuan.gmall.bean.PmsProductInfo;
import com.deyuan.gmall.bean.PmsProductSaleAttr;
import com.deyuan.gmall.service.PmsProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class spuController {
    @Reference
    PmsProductService pmsProductService;

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id)
    {
       List<PmsProductInfo> infos= pmsProductService.getSpuList(catalog3Id);
       return infos;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList()
    {
        List<PmsBaseSaleAttr> baseSaleAttrs=pmsProductService.getbaseSaleAttrList();
        return baseSaleAttrs;
    }


    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile)
    {
        //处理fastdfs图片上传


        return "success";
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo)
    {

        return "success";
    }

}
