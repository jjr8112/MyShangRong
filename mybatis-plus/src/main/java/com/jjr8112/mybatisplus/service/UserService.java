package com.jjr8112.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jjr8112.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> listAllByName(String name);
}
