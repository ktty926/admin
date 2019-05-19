package com.jk.bean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "phoneCount")
public class PhoneCount implements Serializable {
    private String id;
    private String phoneNumber;
    private Integer status;//状态为1  黑名单；  为2 正常
    private List<PhoneCount> PhoneCounts;
}
