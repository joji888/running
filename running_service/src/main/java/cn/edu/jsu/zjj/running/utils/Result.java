package cn.edu.jsu.zjj.running.utils;

/**
 * 结果封装类
 * @param <T>
 */
public class Result<T> {
    private T data;         //结果数据
    private Integer code;   //结果状态
    private String message; //结果信息

    //用于集合数据统计总条数
    private Integer count;

    private Result(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    /**
     * 失败 结果集封装
     * @param massage   消息
     * @param <T>       泛型
     * @return
     */
    public static<T> Result<T> error(String massage){
        return new Result<T>(null,500,massage);
    }
    public static<T> Result<T> error(){
        return error("");
    }

    /**
     * 成功 结果集封装
     * @param data      结果数据
     * @param message   消息
     * @param <T>       泛型
     * @return
     */
    public static<T> Result<T> success(T data, String message){
        return new Result<T>(data,200,message);
    }
    public static<T> Result<T> success(String massage){
        return success(null,massage);
    }
    public static<T> Result<T> success(T data){
        return success(data,"");
    }
    public static<T> Result<T> success(){
        return success(null,"");
    }

    public T getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
