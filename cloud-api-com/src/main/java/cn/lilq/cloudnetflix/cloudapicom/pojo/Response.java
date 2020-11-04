package cn.lilq.cloudnetflix.cloudapicom.pojo;

import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 19:46
 */
public class Response {
    private Integer code;
    private String errorMsg;
    private Object data;

    public Response(Integer code, String errorMsg, Object data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(code, response.code) &&
                Objects.equals(errorMsg, response.errorMsg) &&
                Objects.equals(data, response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, errorMsg, data);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
