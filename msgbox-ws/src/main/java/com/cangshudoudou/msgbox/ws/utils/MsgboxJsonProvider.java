package com.cangshudoudou.msgbox.ws.utils;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class MsgboxJsonProvider extends JacksonJaxbJsonProvider {
    public void setMapper(ObjectMapper m) {
        super.setMapper(m);
        //configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
        m.setSerializationInclusion(Inclusion.NON_NULL);
        //configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
    }
}
