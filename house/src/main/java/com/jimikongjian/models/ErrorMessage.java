package com.jimikongjian.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;



/**
 * Created by fuxiuyang on 17/2/18.
 * @author fuxiuyang
 */
@Repository("errorMessage")
@Scope("prototype")
public class ErrorMessage{



    private int errorCode;

    private String message;

    private String messageCn;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCn() {
        return messageCn;
    }

    public void setMessageCn(String messageCn) {
        this.messageCn = messageCn;
    }
}
