package com.cache.sprite.handler;

import com.cache.sprite.Bean;
import com.cache.sprite.impl.Cached;
import com.cache.sprite.impl.Raw;
import com.cache.sprite.util.BeanType;
import com.configuration.data.StreamHash;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 */
public class SpriteHandler {

    private static final Map<BeanType, List<Bean>> BEANS = new HashMap<BeanType, List<Bean>>();

    public static Cached getCachedBean(int value) {
        return (Cached) BEANS.get(BeanType.CACHED).get(value);
    }

    public static Raw getRawBean(int value) {
        return (Raw) BEANS.get(BeanType.RAW).get(value);
    }

    public static List<Bean> getBeanList(BeanType beanType) {
        return BEANS.get(beanType);
    }

    public static void removeRawBean(Raw raw) {
        final BeanType beanType = raw.getBeanType();
        if (BEANS.get(beanType).contains(raw)) {
            BEANS.get(beanType).remove(raw);
            final List<Bean> list = new ArrayList<Bean>();
            for (Bean array : BEANS.get(beanType)) {
                list.add(array);
            }
            BEANS.put(beanType, list);
        }
    }

    public static void removeCachedBean(Cached cached) {
        final BeanType beanType = cached.getBeanType();
        if (BEANS.get(beanType).contains(cached)) {
            cached.remove();
        }
    }

    private static void check() {
        if (!BEANS.containsKey(BeanType.CACHED)) {
            BEANS.put(BeanType.CACHED, new ArrayList<Bean>());
        }
        if (!BEANS.containsKey(BeanType.RAW)) {
            BEANS.put(BeanType.RAW, new ArrayList<Bean>());
        }
    }

    public static void submit(Bean bean) {
        check();
        if (bean != null) {
            bean.setHash(StreamHash.getStreamHash(new ByteArrayInputStream(bean.getBytes())));
            BEANS.get(bean.getBeanType()).add(bean);
        }
    }

    public static void refreshBeans() {
        BEANS.clear();
        check();
    }

}
