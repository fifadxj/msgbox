package info.cangshudoudou.msgbox.springmvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.multiaction.AbstractUrlMethodNameResolver;

public class MsgboxUrlMethodNameResolver extends AbstractUrlMethodNameResolver {
    private Map<String, String> methodMappings = new HashMap<String, String>();

    private String defaultMethodName;

    public void setDefaultMethodName(String defaultMethodName) {
        this.defaultMethodName = defaultMethodName;
    }


    public void registerMethodMapping(String urlPath, String method) {
        methodMappings.put(urlPath, method);
    }

    @Override
    protected String getHandlerMethodNameForUrlPath(String urlPath) {
        String method = methodMappings.get(urlPath);
        if (! StringUtils.hasText(method)) {
            method = defaultMethodName;
        }
        
        return method;
    }

}
