package com.livegoods.commons;

import lombok.Data;

/**
 * @Auther: Elton Ge
 * @Date: 17/9/20
 * @Description: com.livegoods.commons
 * @version: 1.0
 */
@Data
public class Results {
    private int status;
    private Object results;
    private String msg;
    private Object data;
    private boolean hasMore;
    private long time;



    public void setTime(long time) {
        this.time = time;
    }

    public static Results ok(){
        Results result = new Results();
        result.setStatus(200);
        return result;
    }

    public static Results ok(Object data){
        Results result = new Results();
        result.setStatus(200);
        result.setData(data);
        return result;
    }

    public static Results error(){
        Results result = new Results();
        result.setStatus(500);
        return result;
    }

    public static Results error(String msg){
        Results result = new Results();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }
}
