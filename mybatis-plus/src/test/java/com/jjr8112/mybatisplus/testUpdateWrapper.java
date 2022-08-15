package com.jjr8112.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jjr8112.mybatisplus.entity.User;
import com.jjr8112.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class testUpdateWrapper {
    @Resource
    private UserMapper userMapper;

    /**
     * 查询，名字中含n 且 年龄小于18或email为空的用户
     * 更新，设置用户年龄为18，邮箱设为 user@my.com
     */
    @Test
    public void testUpdateWrapper1(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 这样的组装方式是无法自动填充updateTime的
        updateWrapper
                .set("age",18)
                .set("email","user@my.com")
                .like("name", "n")
                .and( i -> i
                        .lt("age",18)
                        .or()
                        .isNull("email"));

//        User user = new User();
//        user.setAge(18);
//        user.setEmail("user@my.com");
        // 新建User对象，使之包含updateTime字段，便于自动填充
        User user = new User();
        int res = userMapper.update(user, updateWrapper);
        System.out.println("更新的记录数：" + res);
    }

    /**
     * 查询，名字中包含n、年龄大于100且小于400的用户
     * 查询条件来自用户输入，可选
     */
    @Test
    public void testCondition(){

        String name = null;
        Integer ageBegin = null;
        Integer ageEnd = 400;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(name),"name","n");


//        if (ageBegin != null && ageEnd !=null){
//            queryWrapper
//                    .between("age",ageBegin,ageEnd);
//
//        }
        //这种写法和上面的有区别吗？（从条件组装的角度考虑，上面的写法无法针对单个age条件进行组装）
        queryWrapper.ge(ageBegin != null, "age",ageBegin);
        queryWrapper.le(ageEnd != null, "age",ageEnd);


        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 使用LambdaQueryWrapper
     */
    @Test
    public void testLambdaQueryWrapper(){
        String name = null;
        Integer ageBegin = null;
        Integer ageEnd = 400;

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(name),User::getName,"n");


//        if (ageBegin != null && ageEnd !=null){
//            queryWrapper
//                    .between("age",ageBegin,ageEnd);
//
//        }
        //这种写法和上面的有区别吗？（从条件组装的角度考虑，上面的写法无法针对单个age条件进行组装）
        queryWrapper.ge(ageBegin != null, User::getAge,ageBegin);
        queryWrapper.le(ageEnd != null, User::getAge,ageEnd);


        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 使用LambdaUpdateWrapper
     * 实体属性出现的位置都使用lambda表达式，避免编译时出错遗留在运行阶段
     */
    @Test
    public void testLambdaUpdateWrapper(){
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        // 这样的组装方式是无法自动填充updateTime的
        lambdaUpdateWrapper
                .set(User::getAge,18)
                .set(User::getEmail,"user@my.com")
                .like(User::getName, "n")
                .and( i -> i
                        .lt(User::getAge,18)
                        .or()
                        .isNull(User::getEmail));

//        User user = new User();
//        user.setAge(18);
//        user.setEmail("user@my.com");
        // 新建User对象，使之包含updateTime字段，便于自动填充
        User user = new User();
        int res = userMapper.update(user, lambdaUpdateWrapper);
        System.out.println("更新的记录数：" + res);
    }
}
