package com.briup.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLog {
    private Integer id;
    private String message;
    private LocalDateTime createTime;
}
