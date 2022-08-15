package com.jjr8112.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjr8112.mybatisplus.entity.User;
import com.jjr8112.mybatisplus.mapper.UserMapper;
import com.jjr8112.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Resource
//    private UserMapper userMapper;

    @Override
    public List<User> listAllByName(String name){
//        return userMapper.selectAllByName(name);
        return baseMapper.selectAllByName(name);
    }
}
