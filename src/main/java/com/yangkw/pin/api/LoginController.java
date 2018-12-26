package com.yangkw.pin.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.yangkw.pin.domain.login.LoginInfoDTO;
import com.yangkw.pin.domain.response.LoginResponse;
import com.yangkw.pin.domain.user.UserDO;
import com.yangkw.pin.domain.user.UserToken;
import com.yangkw.pin.infrastructure.register.TokenManager;
import com.yangkw.pin.service.UserService;
import com.yangkw.pin.service.util.CheckUtil;
import com.yangkw.pin.service.util.ResponseUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类LoginController.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-21
 */
@RestController
@RequestMapping("auth")
public class LoginController {
    private static Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private WxMaService wxService;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("login")
    public LoginResponse login(@RequestBody @Validated LoginInfoDTO info, BindingResult bindingResult) {
        LoginResponse response = new LoginResponse();
        if (bindingResult.hasErrors()) {
            return ResponseUtil.errorResponse(response, CheckUtil.error(bindingResult));
        }
        WxMaJscode2SessionResult result = null;
        try {
            result = wxService.getUserService().getSessionInfo(info.getCode());
        } catch (WxErrorException e) {
            LOG.warn("login fail/wx error e:{}", e.getMessage());
        }
        if (result == null) {
            LOG.warn("session result is null code:{}", info.getCode());
            return ResponseUtil.errorResponse(response, "session result is null");
        }
        UserDO old = userService.find(result.getOpenid());
        Integer id;
        if (old == null) {
            id = userService.insert(info, result.getOpenid());
        } else {
            id = old.getId();
            userService.update(info, result.getOpenid());
        }
        UserToken userToken = tokenManager.generateToken(id, result.getSessionKey(), result.getOpenid());
        response.setSuccess(true);
        response.setToken(userToken.getToken());
        response.setExpireDate(userToken.getExpireTime().toString());
        return response;
    }
}
