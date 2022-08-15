package com.jjr8112.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充某些字段，即将填充任务从业务逻辑中单独抽取出来
 */

/**
 * 自动填充并不是说有需要填默认值就使用这种实现方式，这会增加程序的复杂度
 * 默认值可以在通过sql语句实现，也可以在业务逻辑中实现
 * 只有2种情况使用自动填充机制才是合适的：
 * 1.需要填充的字段几乎出现在左右表中（如create_time、update_time字段）
 * 2.项目较小表较少，自动填充默认值一目了然
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    public static final String string = "石头";
    public static final Integer integer = 500;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("user表insert时间戳自动填充。。。");
        // 元数据对象，想填充的列名，列对应的数据类型，想要填充的内容
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());


        /**
         * 自动填充第1种优化情况：判断当前元数据中是否含有填充项
         */
        if (metaObject.hasSetter("author")){
            log.info("author表insert填充》》》");
            this.strictInsertFill(metaObject, "author", String.class, string);
        }

        /**
         * 当insert操作时有值时该自动填充仍被执行，但实际上没有必要
         * 自动填充第2种优化情况：判断需要填充的字段是否已经有值，有则不执行
         */
        if (this.getFieldValByName("age", metaObject) == null){
            log.info("user表insert填充age!!!");
            this.strictInsertFill(metaObject, "age", Integer.class, integer);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("user表update时间戳自动填充。。。");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

    }
}
