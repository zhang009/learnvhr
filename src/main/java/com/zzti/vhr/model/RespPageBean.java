package com.zzti.vhr.model;

import java.util.List;

/**
 * @ClassName RespPageBean
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/11 11:40
 **/

public class RespPageBean {
    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
