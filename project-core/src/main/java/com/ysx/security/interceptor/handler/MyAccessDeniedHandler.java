package com.ysx.security.interceptor.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 *<p>  Denied是拒签的意思自定义403响应的内容,让他返回我们的错误逻辑提示 <p> 
 *  
 * @author yangShiXiong  
 * @Data 2020年12月22日
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp,
                       AccessDeniedException e) throws IOException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString("权限不足，请联系管理员!"));
        out.flush();
        out.close();
    }
}
