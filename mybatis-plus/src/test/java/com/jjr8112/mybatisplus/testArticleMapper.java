package com.jjr8112.mybatisplus;

import com.jjr8112.mybatisplus.entity.Article;
import com.jjr8112.mybatisplus.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class testArticleMapper {

    @Resource
    private ArticleMapper articleMapper;

    @Test
    public void testInsert(){
        Article article = new Article();
        article.setContent("空1");
//        article.setAuthor("tata");
        articleMapper.insert(article);
    }

    @Test
    public void testDelete(){
        int result = articleMapper.deleteById(1556297607558664194l);
        System.out.println(result);

    }

    @Test
    public void testSelect(){
        List<Article> articles = articleMapper.selectList(null);
        System.out.println(articles);
    }

    @Test
    public void testSelect2(){
        Article article = articleMapper.selectById(1559000644546859009L);
        System.out.println(article);

    }

    @Test
    public void testInsertFile(){
        String path = "D:\\code\\MyShangRong-prepare\\mybatis-plus\\src\\main\\java\\com\\jjr8112\\mybatisplus\\entity\\Article.java";
        File file = new File(path);
        List<String> list = new ArrayList<String>();
        String[][] strings = null;

        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf8");
            BufferedReader bw = new BufferedReader(isr);
            String line = null;
            while((line = bw.readLine()) != null){
                //System.out.println(line);
                list.add(line);
            }
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<list.size(); i++){
            stringBuilder.append(list.get(i));
        }

//        strings = new String[list.size()][14];
//        for(int i=0;i<list.size();i++){
//            String[] st = list.get(i).split("\t");
//            for(int j=0;j<st.length;j++){
//                strings[i][j] = st[j];
//                //System.out.println(strings[i][j]);
//            }
//
//
//        }

        Article article = new Article();
        article.setAuthor("jjr8112");
        article.setContent(stringBuilder.toString());
        int res = articleMapper.insert(article);
        System.out.println(res);
    }
}
