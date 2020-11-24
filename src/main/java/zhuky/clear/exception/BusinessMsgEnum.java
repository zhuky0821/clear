package zhuky.clear.exception;

public enum BusinessMsgEnum {
    UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员！");


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
