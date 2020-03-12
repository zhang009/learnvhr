package com.zzti.vhr.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalException
 * @Description 全局异常处理类
 * @Author Administrator
 * @Date 2020/3/8 10:17
 **/
@RestControllerAdvice
public class GlobalException {

   /* @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e){
        if(e instanceof  MySQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据，操作失败");
        }
        return RespBean.error("数据库异常，操作失败");
    }*/


}
