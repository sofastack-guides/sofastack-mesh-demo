package com.alipay.sofa.ms.reply.context;

import com.alipay.sofa.ms.reply.datasource.DataSourceKey;

public class DynamicDataSourceContext {

    private static final ThreadLocal<String> DATASOURCE_CONTEXT_KEY_HOLDER = new ThreadLocal<>();

    public static void setDataSourceKey(String key) {
        DATASOURCE_CONTEXT_KEY_HOLDER.set(key);
    }

    public static String getDataSourceKey() {
        String key = DATASOURCE_CONTEXT_KEY_HOLDER.get();
        return key == null ? DataSourceKey.PROD_DATABASE : key;
    }

    public static void removeDataSourceKey() {
        DATASOURCE_CONTEXT_KEY_HOLDER.remove();
    }

}