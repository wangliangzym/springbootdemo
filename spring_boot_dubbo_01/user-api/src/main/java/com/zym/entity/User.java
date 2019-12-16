package com.zym.entity;



import lombok.Data;

import java.io.Serializable;

/**
 * @Author wangliang
 * @Date 2019/11/19 0019
 * @Description
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private String hobby;

}
