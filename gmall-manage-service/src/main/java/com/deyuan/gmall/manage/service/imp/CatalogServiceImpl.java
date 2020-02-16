package com.deyuan.gmall.manage.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.deyuan.gmall.bean.PmsBaseCatalog1;
import com.deyuan.gmall.bean.PmsBaseCatalog2;
import com.deyuan.gmall.bean.PmsBaseCatalog3;
import com.deyuan.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.deyuan.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.deyuan.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.deyuan.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CatalogServiceImpl  implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalo1gMapper;
    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getPmsBasseCatalog1() {
        List<PmsBaseCatalog1> pmsBaseCatalog1s = pmsBaseCatalo1gMapper.selectAll();
        return pmsBaseCatalog1s;
    }
    @Override
    public List<PmsBaseCatalog2> getPmsBasseCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2=new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(Integer.parseInt(catalog1Id));
        List<PmsBaseCatalog2> pmsBaseCatalog2s = pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);
        return pmsBaseCatalog2s;
    }
    @Override
    public List<PmsBaseCatalog3> getPmsBasseCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3=new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(Long.parseLong(catalog2Id));
        List<PmsBaseCatalog3> pmsBaseCatalog3s = pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
        return pmsBaseCatalog3s;
    }
}
