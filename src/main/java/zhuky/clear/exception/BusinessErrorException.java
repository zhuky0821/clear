package zhuky.clear.exception;

/**
 * 自定义异常
 */
public class BusinessErrorException extends RuntimeException{
    private String code;
    private String msg;

    public BusinessErrorException(BusinessMsgEnum businessMshEnum){
        this.code = businessMshEnum.getCode();
        this.msg = businessMshEnum.getMsg();
    }

    public BusinessErrorException(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
