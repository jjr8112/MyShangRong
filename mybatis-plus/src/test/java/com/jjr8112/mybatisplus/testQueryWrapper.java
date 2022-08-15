package com.jjr8112.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjr8112.mybatisplus.entity.User;
import com.jjr8112.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class testQueryWrapper {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询，名字包含n、年龄大于等于100且小于等于400、email为空
     */
    @Test
    public void testQueryWrapper1(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 参数1为数据库表的列名
        queryWrapper
                .like("name", "n")
                .between("age", 100,400)
                .isNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 查询，按年龄降序、年龄相同按id升序
     */
    @Test
    public void testQueryWrapper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 删除，email为空的用户
     */
    @Test
    public void testQueryWrapper3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int res = userMapper.delete(queryWrapper);
        System.out.println("删除的记录数：" + res);
    }

    /**
     * 查询，名字中含n 且 年龄小于18或email为空的用户
     * 更新，设置用户年龄为18，邮箱设为 user@my.com
     */
    @Test
    public void testQueryWrapper4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("name", "n")
                .and( i -> i
                        .lt("age",18)
                        .or()
                        .isNull("email"));

        User user = new User();
        user.setAge(18);
        user.setEmail("user@my.com");
        int res = userMapper.update(user, queryWrapper);
        System.out.println("更新的记录数：" + res);

    }

    /**
     * 查询，所有用户的用户名、年龄（组装）
     */
    @Test
    public void testQueryWrapper5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age");
        // 以下方法不能起到只封装两个属性的效果，最终仍会封装为其他属性为null的user对象
//        List<User> list = userMapper.selectList(queryWrapper);
//        list.forEach(System.out::println);
        // 因此 queryWrapper的select方法通常配合userMapper的selectMaps方法一起使用
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 查询，id不大于3的所有用户的id列表
     */
    @Test
    public void testQueryWrapper6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // inSql不建议使用，建议直接在mapper.xml中写子查询
        //queryWrapper.inSql("uid","select uid from user where uid <= 3");
        //queryWrapper.in("uid", 0,1,2,3);
        queryWrapper.le("uid",3);
//        List<User> list = userMapper.selectList(queryWrapper);
//        list.forEach(System.out::println);
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);
    }



}
