package cn.dmx.utils;
import java.io.Serializable;
public class JsonResult implements Serializable{
    private static final long serialVersionUID = 0xb46245f34361521L;
    private Integer code;
    private Object data;
    private String message="";
    public static final int ERROR = 1;
    public static final int SUCCESS = 0;
    private Integer count;

    public JsonResult()
    {
    }
    public JsonResult(Integer code)
    {
        this.code = Integer.valueOf(code);
    }

    public JsonResult(Integer code, Object data, String message, Integer count)
    {
        this.code = code;
        this.data = data;
        this.count = count;
        this.message = message;
    }

    public JsonResult(Throwable e)
    {
        code = Integer.valueOf(1);
        data = null;
        message = e.getMessage();
    }

    public JsonResult(Object data, Integer count)
    {
        code = Integer.valueOf(0);
        this.data = data;
        this.count = count;
        message = "";
    }

    public JsonResult(Object data)
    {
        code = Integer.valueOf(0);
        this.data = data;
        message = "";
    }

    public JsonResult(int code, Throwable e)
    {
        this.code = Integer.valueOf(code);
        data = null;
        message = e.getMessage();
    }
    public JsonResult(int code, String errorMsg)
    {
        this.code = Integer.valueOf(code);
        data = null;
        message = errorMsg;
    }
    public Integer getcode()
    {
        return code;
    }

    public void setcode(Integer code)
    {
        this.code = code;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String toString()
    {
        return (new StringBuilder("JsonResult [code=")).append(code).append(", data=").append(data).append(", message=").append(message).append("]").toString();
    }


}
