package com.yangkw.pin.service.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 类ValidUtil.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-21
 */
public class CheckUtil {
    private CheckUtil() {
    }

    public static String error(BindingResult result) {
        StringBuffer sb = new StringBuffer();
        List<ObjectError> list = result.getAllErrors();
        for (ObjectError error : list) {
            sb.append(error.getDefaultMessage());
        }
        return sb.toString();
    }
}
