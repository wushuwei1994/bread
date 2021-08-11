package com.carrywei.breadspring.inout;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/11
 **/
@Data
public class CommonResp {
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
}
