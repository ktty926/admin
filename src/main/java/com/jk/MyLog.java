package com.jk;

import java.lang.annotation.*;

/**
 * @Author chh
 * @Description //TODO 自定义注解类
 * @Date 0:16 2019/5/3
 * @Param 
 * @return
 **/
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    String value() default "";
}
