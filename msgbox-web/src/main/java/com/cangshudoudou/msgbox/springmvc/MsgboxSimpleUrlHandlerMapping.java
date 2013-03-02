package com.cangshudoudou.msgbox.springmvc;

import java.util.Map;

import org.springframework.beans.BeansException;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

public class MsgboxSimpleUrlHandlerMapping extends SimpleUrlHandlerMapping {
    
    private MsgboxUrlMethodNameResolver methodNameResolver;
    
    private String suffix = "";
    
    private String prefix = "'";
      
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setMethodNameResolver(MsgboxUrlMethodNameResolver methodNameResolver) {
        this.methodNameResolver = methodNameResolver;
    }

    protected void registerHandlers(Map<String, Object> urlMap) throws BeansException {
        if (urlMap.isEmpty()) {
            logger.warn("Neither 'urlMap' nor 'mappings' set on SimpleUrlHandlerMapping");
        }
        else {
            for (Map.Entry<String, Object> entry : urlMap.entrySet()) {
                String url = entry.getKey();
                Object handler = entry.getValue();
                // Prepend with slash if not already present.
                if (!url.startsWith("/")) {
                    url = "/" + url;
                }
                // Remove whitespace from handler bean name.
                if (handler instanceof String) {
                    handler = ((String) handler).trim();
                }
                registerHandler(url, handler);
            }
        }
    }
    
    protected void registerHandler(String urlPath, Object handler) throws BeansException, IllegalStateException {
        urlPath = prefix + urlPath + suffix;
        
        if (handler instanceof String) {
            String handlerName = (String) handler;
            String[] mapping = StringUtils.split(handlerName, ".");
            String beanName;
            String method;
            if (mapping != null) {
                beanName = mapping[0];
                method = mapping[1];
                methodNameResolver.registerMethodMapping(urlPath, method);
            }
            else {
                beanName = handlerName;
            }
            
            handler = beanName;
        }
        
        super.registerHandler(urlPath, handler);
        
    }
    
}
