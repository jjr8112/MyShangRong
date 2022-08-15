package com.jjr8112.mybatisplus;

import com.jjr8112.mybatisplus.entity.Product;
import com.jjr8112.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class testProductMapper {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testSelect(){
        List<Product> products = productMapper.selectList(null);
        products.forEach(System.out::println);
    }
}
