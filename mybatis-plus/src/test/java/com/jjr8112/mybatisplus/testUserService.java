package com.jjr8112.mybatisplus;

import com.jjr8112.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class testUserService {
    @Resource
    private com.jjr8112.mybatisplus.service.UserService userService;

    @Test
    public void testSelect(){
        User id = userService.getById(1);
        System.out.println(id);
    }

    @Test
    public void testSelect2(){
        List<User> userList = userService.list();
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelect3(){
        int count = userService.count();
        System.out.println(count);
    }

    @Test
    public void testSaveBatch(){
        ArrayList<User> users = new ArrayList<>();
        for (int i=0; i<5; i++){
            User user = new User();
//            user.setUid(i + 100);
            user.setAge(i + 15);
            users.add(user);
        }
        userService.saveBatch(users);
    }

    @Test
    public void testListAllByName(){
        List<User> list = userService.listAllByName("Jack");
        list.forEach(System.out::println);
    }
}
