package com.jjr8112.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjr8112.mybatisplus.entity.Product;
import com.jjr8112.mybatisplus.entity.User;
import com.jjr8112.mybatisplus.mapper.ProductMapper;
import com.jjr8112.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class testInterceptor {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testSelectPage(){
        Page<User> pageParam = new Page<>(2, 10);
        userMapper.selectPage(pageParam, null);

        List<User> records = pageParam.getRecords();
        records.forEach(System.out::println);

        long total = pageParam.getTotal();
        System.out.println(total);

        boolean hasNext = pageParam.hasNext();
        System.out.println("是否有下一页？" + hasNext);

        boolean hasPrevious = pageParam.hasPrevious();
        System.out.println("是否有上一页？" + hasPrevious);

    }

    @Test
    public void testSelectByAge(){
        Page<User> userPage = new Page<>(1,10);
        userMapper.selectPageVo(userPage,500);
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);

        long total = userPage.getTotal();
        System.out.println(total);
    }

    /**
     * 模拟冲突测试
     */
    @Test
    public void testConcurrentUpdate(){
        //person1 取数据
        // select * from product where id = 1
        Product p1 = productMapper.selectById(1L);

        //person2 取数据
        // select * from product where id = 1
        Product p2 = productMapper.selectById(1L);

        //person1 修改数据
        // update product set price = 100+50, version = 0+1 where id = 1 and version = 0
        p1.setPrice(p1.getPrice() + 50);
        int res1 = productMapper.updateById(p1);
        System.out.println("person1修改的结果：" + res1);

        //person2 修改数据
        // update product set price = 100-30, version = 0+1 where id = 1 and verison = 0
        // 此处应当失败，重新修改，修改前重新取数据
        // select * from product where id = 1
        // update product set price = 150-30, version = 1+1 where id = 1 and version = 1
        p2.setPrice(p2.getPrice() - 30);
        int res2 = productMapper.updateById(p2);
        if (res2==0){
            //重试
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            res2 = productMapper.updateById(p2);
        }
        System.out.println("person2修改的结果：" + res2);
        //问题：这样的修改时默认person2的修改晚于person1的修改，如何自动修改？

        //person3 看数据
        // select * from product where id = 1
        Product p3 = productMapper.selectById(1L);
        System.out.println(p3.toString());

    }
}
