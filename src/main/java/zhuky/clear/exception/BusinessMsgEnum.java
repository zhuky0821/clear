package zhuky.clear.exception;

public enum BusinessMsgEnum {
    //系统异常
    UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员！"),
    //反射结果异常
    OBJECT_CONVERT_EXCEPTION("501", "查询结果反射出对象出现异常");

    private String code;
    private String msg;

    private BusinessMsgEnum(String code, String msg){
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
