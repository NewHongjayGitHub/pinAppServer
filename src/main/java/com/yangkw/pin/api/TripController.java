package com.yangkw.pin.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类TripController.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
@RestController
@RequestMapping("trip")
public class TripController {
    @PostMapping("fuzzy")
    public Object fuzzy() {
        return null;
    }
}
