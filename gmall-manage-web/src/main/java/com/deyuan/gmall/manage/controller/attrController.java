package com.deyuan.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.deyuan.gmall.bean.PmsBaseAttrInfo;
import com.deyuan.gmall.bean.PmsBaseAttrValue;
import com.deyuan.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class attrController {

    @Reference
    AttrService attrService;

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id)
    {
        List<PmsBaseAttrInfo> attrInfos=attrService.getattrInfoList(catalog3Id);
        return attrInfos;
    }
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId)
    {
        List<PmsBaseAttrValue> attrValueList=attrService.getAttrValueList(attrId);
        return attrValueList;
    }

    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo)
    {
        int i = attrService.saveAttrInfo(pmsBaseAttrInfo);
        if(i!=0)
            return "success";
        else
            return "false";
    }

}
