package com.jimikongjian.service.message;

import com.jimikongjian.models.ErrorMessage;
import com.jimikongjian.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by baidu on 17/2/16.
 * @author fuxiuyang
 * 主要是用来生成错误信息
 */
@Service("errorMessageService")
public class ErrorMessageService {

    @Autowired
    @Qualifier("errorMessage")
    private ErrorMessage errorMessage;


    public ErrorMessage getErrorMessage(int error){
        if (error == Message.SUCCESS){
            return success();
        }else if (error == Message.NO_HAVE_USER){
            return noHaveUser();
        }else if (error == Message.PASSWORD_ERROR){
            return passwordError();
        }else if (error == Message.IS_HAVE_USER){
            return isHaveUser();
        }else if (error == Message.NO_HOUSE){
            return noHouse();
        }else if (error == Message.NO_APPOINT){
            return noAppoint();
        }else if (error == Message.PASSWORD_FORMAT_ERROR){
            return passwordFormatError();
        }else if(error == Message.EMAIL_HAVE_BE_REGISTER){
            return emailHaveBeRegister();
        }else if(error == Message.VERIFICATION_CODE_ERROR){
            return verificationCodeError();
        }else if(error == Message.EMAIL_FORMAT_ERROR){
            return emailFormatError();
        }else if(error == Message.NOT_LOGIN_ACCESS){
            return notLoginAccess();
        }else if(error == Message.SYSTEM_ERROR){
            return systemError();
        }else if(error == Message.UNSUPPORT_METHOD_ERROR){
            return unsupportMethodError();
        }else if(error == Message.PARAMS_ERROR){
            return paramsError();
        }else if (error == Message.REQUEST_TIME_ERROR){
            return requestTimeError();
        }else if(error == Message.NO_PERMISSION_ERROR){
            return noPermissionError();
        }else if (error == Message.NO_SUCH_URI_ERROR){
            return noSuchURIError();
        }else if(error == Message.NO_HAVE_ROOM){
            return noHaveRoom();
        }else if (error == Message.NO_HAVE_ARTICLE){
            return noHaveArticle();
        }else if (error == Message.NO_HAVE_ALBUM){
            return noHaveAlbum();
        }else if (error == Message.NO_HAVE_LIKE){
            return noHaveLike();
        }else if (error == Message.HAS_UNREAD_COMMENT){
            return hasUnreadComment();
        }else {
            return unknownError();
        }

    }

    /**
     * 错误信息封装
     * @param errorCode
     * @param message
     * @param messageCn
     * @return 返回封装后的错误信息
     */
    public ErrorMessage makeError(int errorCode, String message, String messageCn){
        errorMessage.setMessage(message);
        errorMessage.setErrorCode(errorCode);
        errorMessage.setMessageCn(messageCn);
        return errorMessage;
    }

    public ErrorMessage success(){
        return makeError(0,"","");
    }

    /**
     * 没有该用户
     * @return
     */
    public ErrorMessage noHaveUser(){
        return makeError(Message.NO_HAVE_USER,"No Have User","没有该用户");
    }

    /**
     * 密码错误
     * @return
     */
    public ErrorMessage passwordError(){
        return makeError(Message.PASSWORD_ERROR,"Password Error","密码错误");
    }

    /**
     * 已有用户
     * @return
     */
    public ErrorMessage isHaveUser(){
        return makeError(Message.IS_HAVE_USER,"Is Have User","账户已注册");
    }
    
    /**
     * 没有符合的房源
     * @return
     */
    public ErrorMessage noHouse(){
        return makeError(Message.NO_HOUSE,"No House","没有符合的房源");
    }
    
    /**
     * 没有符合的预约信息
     * @return
     */
    public ErrorMessage noAppoint(){
        return makeError(Message.NO_APPOINT,"No Appoint","没有符合的预约信息");
    }
    
    /**
     * 密码格式错误
     * @return
     */
    public ErrorMessage passwordFormatError(){
        return makeError(Message.PASSWORD_FORMAT_ERROR,"Password Format Error","密码格式错误");
    }

    /**
     * 邮箱已被注册
     * @return
     */
    public ErrorMessage emailHaveBeRegister(){
        return makeError(Message.EMAIL_HAVE_BE_REGISTER,"MailService Have Be Register","邮箱已被注册");
    }

    /**
     * 验证码不正确
     * @return
     */
    public ErrorMessage verificationCodeError(){
        return makeError(Message.VERIFICATION_CODE_ERROR,"Verification Code Error","验证码不正确");
    }

    /**
     * 邮箱格式不正确
     * @return
     */

    public ErrorMessage emailFormatError(){
        return makeError(Message.EMAIL_FORMAT_ERROR,"MailService Format Error","邮箱格式不正确");
    }

    /**
     * 未登录访问
     * @return
     */
    public ErrorMessage notLoginAccess(){
        return makeError(Message.NOT_LOGIN_ACCESS, "Not Login Access", "未登录访问");

    }

    /**
     * 系统错误
     * @return
     */
    public ErrorMessage systemError(){
        return makeError(Message.SYSTEM_ERROR, "System Error", "系统错误");
    }

    /**
     * 不支持的方法错误
     * @return
     */
    public ErrorMessage unsupportMethodError(){
        return makeError(Message.UNSUPPORT_METHOD_ERROR, "Unsupport method error", "不支持的方法错误");
    }

    /**
     * 参数错误
     * @return
     */
    public ErrorMessage paramsError(){
        return makeError(Message.PARAMS_ERROR, "Params Error", "参数错误");
    }

    /**
     * 时间有误
     * @return
     */
    public ErrorMessage requestTimeError(){
        return makeError(Message.REQUEST_TIME_ERROR, "Request Time Error", "请求时间有误");
    }

    /**
     * 没有权限
     * @return
     */
    public ErrorMessage noPermissionError(){
        return makeError(Message.NO_PERMISSION_ERROR, "No Permission Error", "没有权限");
    }

    /**
     * 该URI路径不存在
     * @return
     */
    public ErrorMessage noSuchURIError(){
        return makeError(Message.NO_SUCH_URI_ERROR, "No Such URI Error", "该URI路径不存在");
    }

    /**
     * 未知错误
     * @return
     */
    public ErrorMessage unknownError(){
        return makeError(Message.UNKNOWN_ERROR, "Unknown Error", "未知错误");
    }

    /**
     * 该空间不存在
     * @return
     */
    public ErrorMessage noHaveRoom(){
        return makeError(Message.NO_HAVE_ROOM, "No Have Room", "空间不存在");
    }

    /**
     * 该文章不存在
     * @return
     */
    public ErrorMessage noHaveArticle(){
        return makeError(Message.NO_HAVE_ARTICLE, "No Have Article", "文章不存在");
    }

    /**
     * 该文章不存在
     * @return
     */
    public ErrorMessage noHaveAlbum(){
        return makeError(Message.NO_HAVE_ALBUM, "No Have Album", "相册不存在");
    }

    /**
     * 没有收藏该文章
     * @return
     */
    public ErrorMessage noHaveLike(){
        return makeError(Message.NO_HAVE_LIKE, "No Have Like", "没有收藏该文章");
    }

    /**
     * 已经存在该未读评论
     * @return
     */
    public ErrorMessage hasUnreadComment(){
        return makeError(Message.HAS_UNREAD_COMMENT, "Has Unread Comment", "已经存在该未读评论");
    }
}
