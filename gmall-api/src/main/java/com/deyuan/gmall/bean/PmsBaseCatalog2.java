package com.deyuan.gmall.bean;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class PmsBaseCatalog2 implements Serializable {
    @Id
    private Integer id;

    private String name;

    private Integer catalog1Id;

    private List<PmsBaseCatalog3> catalog3s;

    public List<PmsBaseCatalog3> getCatalog3s() {
        return catalog3s;
    }

    public void setCatalog3s(List<PmsBaseCatalog3> catalog3s) {
        this.catalog3s = catalog3s;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(Integer catalog1Id) {
        this.catalog1Id = catalog1Id;
    }
}