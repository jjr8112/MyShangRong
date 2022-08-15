package com.jjr8112.mybatisplus;

import com.jjr8112.mybatisplus.entity.User;
import com.jjr8112.mybatisplus.mapper.UserMapper;
import com.jjr8112.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class testUserMapper {

    //    @Autowired
    @Resource
    private UserMapper userMapper;


    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectlist(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    void testSelect(){
        System.out.println(userMapper.selectById(2));
    }

    @Test
    public void testIncert(){
        User user = new User();
//        user.setAge(110);
        user.setName("lala");
        user.setEmail("lala@my.com");
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    public void testSelect2(){
        List<User> list = userMapper.selectBatchIds(Arrays.asList(3,1));
        list.forEach(System.out::println);

    }

    @Test
    public void testSelect3(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","hh");
        map.put("email","hh@my.com");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUid(1556208001215504386L);
        user.setAge(23);
        user.setEmail("yaya@my.com");
        user.setName("yaya");
//        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    @Test
    public void testDelete(){
        int result = userMapper.deleteById(101L);
        System.out.println(result);
    }

    @Test
    public void testSelectAllByName(){
        List<User> list = userMapper.selectAllByName("Jack");
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectAgeByName(){
        List<User> list = userMapper.selectAgeByName("gaga");
        list.forEach(System.out::println);
    }



}
