package com.cangshudoudou.msgbox.utils;

import java.util.Date;

public class SessionData {
    private String langugage;
    private Object authenticated;
    private Date loginDate;
    
    public Date getLoginDate() {
        return loginDate;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    public String getLangugage() {
        return langugage;
    }
    public void setLangugage(String langugage) {
        this.langugage = langugage;
    }
    public Object getAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(Object authenticated) {
        this.authenticated = authenticated;
    }
    
    
}
