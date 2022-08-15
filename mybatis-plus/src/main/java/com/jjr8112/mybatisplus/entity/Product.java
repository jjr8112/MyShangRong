package com.jjr8112.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
@TableName(value = "product")
public class Product {
    private Long Id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
