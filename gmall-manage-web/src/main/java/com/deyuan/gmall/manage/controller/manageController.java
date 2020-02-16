package com.deyuan.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.deyuan.gmall.bean.PmsBaseCatalog1;
import com.deyuan.gmall.bean.PmsBaseCatalog2;
import com.deyuan.gmall.bean.PmsBaseCatalog3;
import com.deyuan.gmall.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class manageController {
    @Reference
    CatalogService catalogService;
    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1()
    {

        return catalogService.getPmsBasseCatalog1();
    }
    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id)
    {

        return catalogService.getPmsBasseCatalog2(catalog1Id);
    }
    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id)
    {

        return catalogService.getPmsBasseCatalog3(catalog2Id);
    }
}
