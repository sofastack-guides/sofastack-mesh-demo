package com.alipay.sofa.ms.endpoint.filter;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.util.Set;


/**
 * to add a self define filter to assure the port of 8080 can access the data exported on 8341 by rest service
 *
 * <p/>
 * Created by yangguanchao on 16/9/12.
 */
@Provider
@PreMatching
public class CommonContainerResponseFilter extends CorsFilter {

    public void setAllowedOrigins(Set<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

}
