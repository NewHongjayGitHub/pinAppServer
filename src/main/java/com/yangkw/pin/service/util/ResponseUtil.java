package com.yangkw.pin.service.util;

import com.yangkw.pin.domain.response.BaseResponse;

/**
 * 类ResponseUtil.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-22
 */
public class ResponseUtil {
    private ResponseUtil() {
    }

    public static <T extends BaseResponse> T errorResponse(T response, String errorMessage) {
        response.setMessage(errorMessage);
        response.setSuccess(false);
        return response;
    }
}
