package com.vc.wd.common.bean.shop;

import java.util.List;

/**
 * desc
 * author VcStrong
 * github VcStrong
 * date 2020/5/28 1:42 PM
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
