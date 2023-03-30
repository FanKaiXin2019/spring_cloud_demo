package fcy.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/27 14:22
 */
@RestControllerAdvice
public class EController{

    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        return "这是一个全局处理异常 "  + e.getMessage();
    }

}
