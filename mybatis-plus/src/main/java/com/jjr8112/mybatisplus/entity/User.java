package com.jjr8112.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 四大常用注解
 * 1.@TableName     映射表名
 * 2.@TableId       标注主键及主键策略
 * 3.@TableField    映射列名并配合自动填充
 * 4.@TableLogic    逻辑删除
 */
@Data
@TableName(value = "user")
public class User {


    /**
     * 雪花算法策略不生效解决过程：
     * 1.将long改为对应的包装类Long
     * 2.将MySQL中对应数据类型改为BIGINT（雪花算法id64位，转二进制最大可达20位）
     */
    /**
     * value = "表中主键名"           改变表名而不改变代码
     * type = "IdType.ASSIGN_ID"    id生成应用雪花算法
     * type = "IdType.AUTO"         id生成策略应用自增策略(数据库id需设为自增id)
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Integer age;

    private String email;


    /**
     * 程序命名方式——驼峰，数据库命名方式——下划线，mybatis-plus会进行自动转换
     * 此外其他情况的数据库列名均无法自动转换，需使用@TableField注解配合
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 数据项自动生成方式：
     * 1.sql语句给定默认初值
     * 2.datetime类型根据当前时间戳更新（对应sql语句是CURRENT_TIMESTAMP， ALTER TABLE tab_name  MODIFY COLUMN update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP）
     * 3.@TableField的自动填充功能
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * MySQL无布尔类型，用tinyint实现，代码中用Boolean和Integer均可
     * 逻辑删除机制默认 0表示未删false，1表示已删true
     * 若需要调整为 1未删除， -1已删除， 需在配置文件中进行配置
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;
}
