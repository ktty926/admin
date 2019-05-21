package com.jk.bean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection = "logLogin1")
public class LogLogin {
    private String id;

    private String username; //用户名

    private String operation; //操作

    private String method; //方法名

    private String params; //参数

    private String ip; //ip地址

    private Date createDate;
}
