package com.cangshudoudou.msgbox.ws.endpoint;

import com.cangshudoudou.msgbox.ws.vo.LoginRequest;
import com.cangshudoudou.msgbox.ws.vo.LoginResponse;
import com.cangshudoudou.msgbox.ws.vo.LogoutRequest;
import com.cangshudoudou.msgbox.ws.vo.LogoutResponse;

public interface SecurityEndpoint {
	LoginResponse login(LoginRequest request);
	LogoutResponse logout(LogoutRequest request);
}
