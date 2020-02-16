package com.deyuan.gmall.service;

import com.deyuan.gmall.bean.PmsBaseCatalog1;
import com.deyuan.gmall.bean.PmsBaseCatalog2;
import com.deyuan.gmall.bean.PmsBaseCatalog3;

import java.io.Serializable;
import java.util.List;

public interface CatalogService extends Serializable {

    List<PmsBaseCatalog1> getPmsBasseCatalog1();
    List<PmsBaseCatalog2> getPmsBasseCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getPmsBasseCatalog3(String catalog2Id);
}
