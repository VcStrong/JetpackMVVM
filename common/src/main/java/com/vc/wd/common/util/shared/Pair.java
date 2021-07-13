package com.vc.wd.common.util.shared;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Unique;

/**
 * author dingtao
 * date   5/13/21 4:24 PM
 * desc
 */
@Entity
public class Pair {
    @Id
    public long id;
    @Unique
    public String key;
    public String value;

    public Pair() {
    }

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
