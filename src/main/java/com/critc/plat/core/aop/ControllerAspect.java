package com.critc.plat.core.aop;

import com.critc.plat.util.json.JsonUtil;
import com.critc.plat.util.string.StringUtil;
import com.critc.plat.util.web.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/6/22:20
 */
@ControllerAdvice
public class ControllerAspect {
    private static Logger logger = LoggerFactory.getLogger("controllerLog");

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        logger.error(ex.getMessage());
        //判断是否是Ajax请求
        boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
        //获取异常的详细信息
        if (isAjaxRequest) {
            String msg = "{\"flag\":false,\"msg\":" + ex.getMessage() + "}";
            WebUtil.out(response, JsonUtil.toStr(msg));
            return null;
        } else {
            //URL请求处理
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", ex.getMessage());
            map.put("isError", true);
            map.put("exceptionName", ex.getMessage());
            return new ModelAndView("exception", map);
        }
    }
}
