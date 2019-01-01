package com.yangkw.pin.domain.request;

import javax.validation.constraints.NotNull;

/**
 * 类OwnOrderRequest.java
 *
 * @author kaiwen.ykw 2018-12-31
 */
public class OwnOrderRequest extends BaseRequest {
    @NotNull(message = "page can't null")
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
