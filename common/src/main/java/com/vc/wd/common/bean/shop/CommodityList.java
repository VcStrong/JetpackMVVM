package com.vc.wd.common.bean.shop;

import java.util.List;

/**
 * @author dingtao
 * @date 2019/1/3 10:52
 * qq:1940870847
 */
public class CommodityList {

    List<Commodity> commodityList;
    long id;
    String name;

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
