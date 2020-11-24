package zhuky.clear.exception;

/**
 * 统一返回对象
 * @param <T>
 */
public class JsonResult<T> {
    private T data;
    private String code;
    private String msg;

    /**
     * 若没有数据返回，默认状态码为0，提示信息为：操作成功！
     */
    public JsonResult(){
        this.code = "0";
        this.msg = "操作成功！";
    }

    /**
     * 若有数据返回，可以认为指定状态码和提示信息
     * @Param code
     * @param msg
     */
    public JsonResult(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 若有数据返回,，状态码为0，提示信息为：操作成功！
     * @param data
     */
    public JsonResult(T data){
        this.data = data;
        this.code = "0";
        this.msg = "操作成功！";
    }

    /**
     * 若有数据返回，状态码为0，认为指定提示信息
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg){
        this.data = data;
        this.code = "0";
        this.msg = msg;
    }

    /**
     * 使用自定义异常作为参数传递状态码和提示信息
     * @param mshEnum
     */
    public JsonResult(BusinessMsgEnum msgEnum){
        this.code = msgEnum.getCode();
        this.msg = msgEnum.getMsg();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
