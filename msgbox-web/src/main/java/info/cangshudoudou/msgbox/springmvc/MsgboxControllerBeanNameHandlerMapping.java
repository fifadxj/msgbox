package info.cangshudoudou.msgbox.springmvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.AbstractControllerUrlHandlerMapping;

public class MsgboxControllerBeanNameHandlerMapping extends AbstractControllerUrlHandlerMapping {
    private static final String CONTROLLER_SUFFIX = "Controller";
    private String urlPrefix = "";

    private String urlSuffix = "";
    
    private String clearWord = CONTROLLER_SUFFIX;
    

    public void setClearWord(String clearWord) {
        this.clearWord = clearWord;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = (urlPrefix != null ? urlPrefix : "");
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = (urlSuffix != null ? urlSuffix : "");
    }

    @Override
    protected String[] buildUrlsForHandler(String beanName, Class beanClass) {
        List<String> urls = new ArrayList<String>();
        urls.add(generatePathMapping(beanName));
        String[] aliases = getApplicationContext().getAliases(beanName);
        for (String alias : aliases) {
            urls.add(generatePathMapping(alias));
        }
        return StringUtils.toStringArray(urls);
    }

    protected String generatePathMapping(String beanName) {
        String name = (beanName.startsWith("/") ? beanName : "/" + beanName);
        StringBuilder path = new StringBuilder();
        if (!name.startsWith(this.urlPrefix)) {
            path.append(this.urlPrefix);
        }
        path.append(name);
        if (!name.endsWith(this.urlSuffix)) {
            path.append(this.urlSuffix);
        }
        return path.toString().replaceAll(clearWord, "");
    }
}
