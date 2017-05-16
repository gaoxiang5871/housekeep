package com.jimikongjian.service.message;

import com.jimikongjian.models.Message;
import com.jimikongjian.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxiuyang on 17/2/17.
 * @author fuxiuyang
 */
@Service("responseMessageService")
public class ResponseMessageService {


    @Autowired
    @Qualifier("errorMessageService")
    private ErrorMessageService errorMessageService;

    /**
     * 将系统需要返回给前端的信息进行一次封装
     * @param result
     * @param message
     * @return 返回封装后的信息
     */
    public ResponseMessage makeMessage(List result, int message){
        ResponseMessage responseMessage = new ResponseMessage();
        if (message == Message.SUCCESS){
            responseMessage.setResult(result);
            responseMessage.setSuccess(true);

        }else{
            responseMessage.setErrors(errorMessageService.getErrorMessage(message));
            responseMessage.setResult(result);
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }

    /**
     * 将系统需要返回给前端的信息进行一次封装
     * @param result
     * @param message
     * @return 返回封装后的信息
     */
    public ResponseMessage makeMessage(Object result, int message){
        List<Object> resultList = new ArrayList<Object>();
        resultList.add(result);
        return makeMessage(resultList,message);
    }

    /**
     * 只传入一个信息码
     * @param message
     * @return
     */
    public ResponseMessage makeMessage(int message){
        return makeMessage(null,message);
    }

}
