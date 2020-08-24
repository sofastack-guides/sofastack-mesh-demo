package com.alipay.sofa.ms.client.datasource;

import com.alipay.sofa.ms.client.context.DynamicDataSourceContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContext.getDataSourceKey();
    }
}
