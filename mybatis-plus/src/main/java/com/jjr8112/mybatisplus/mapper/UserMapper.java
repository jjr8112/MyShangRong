package com.jjr8112.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjr8112.mybatisplus.entity.User;

import java.util.List;

//@Service
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllByName(String name);

    List<User> selectAgeByName(String name);

    /**
     * 根据年龄查询用户列表，并分页展示
     * @param page
     * @param age
     * @return
     */
    IPage<User> selectPageVo(Page<?>page, Integer age);
}