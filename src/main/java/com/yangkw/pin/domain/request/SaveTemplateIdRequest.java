package com.yangkw.pin.domain.request;


/**
 * 类saveTemplateIdRequest.java
 *
 * @author kaiwen.ykw 2019-01-02
 */
public class SaveTemplateIdRequest extends BaseRequest {
    private String templateId;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
