package com.vc.wd.common.util.shared;

import com.vc.wd.common.core.WDApplication;
import com.vc.wd.common.util.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import io.objectbox.Box;

/**
 * author dingtao
 * date   5/13/21 4:22 PM
 * desc
 */
public class SharedUtils {
    private static Logger logger = Logger.createLogger(SharedUtils.class);
    private final static String VALUE = "value";
    private Box<Pair> box;
    private static SharedUtils instance;

    private SharedUtils() {
        box = WDApplication.getBoxStore().boxFor(Pair.class);
    }

    public static SharedUtils getInstance() {
        if (instance == null) {
            synchronized (SharedUtils.class) {
                if (instance == null) {
                    instance = new SharedUtils();
                }
            }
        }
        return instance;
    }

    public static void put(String key, Object value) {
        JSONObject valueJson = new JSONObject();
        try {
            valueJson.put(VALUE, value);
            Pair pair = get(key);
            if (pair == null) {
                pair = new Pair(key, valueJson.toString());
            } else {
                pair.value = valueJson.toString();
            }
            getInstance().box.put(pair);
        } catch (Exception e) {
            logger.e(e);
        }
    }

    private static Pair get(String key) {
        Pair pair = getInstance().box.query()
                .equal(Pair_.key, key)
                .build().findUnique();
        return pair;
    }

    public static boolean getBoolen(String key) {
        return getBoolen(key, false);
    }

    public static boolean getBoolen(String key, boolean defValue) {
        Pair pair = get(key);
        if (pair != null) {
            try {
                JSONObject jsonObject = new JSONObject(pair.value);
                return jsonObject.getBoolean(VALUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }
}
