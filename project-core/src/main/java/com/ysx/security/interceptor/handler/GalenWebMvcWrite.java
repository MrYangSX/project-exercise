package com.ysx.security.interceptor.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysx.utils.result.ResultReturn;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 *<p> 页面信息 <p> 
 *  
 * @author yangShiXiong  
 * @Data 2020年12月21日
 */
public class GalenWebMvcWrite {
    
	/**
	 * 
	 * todo : 输出信息到页面
	 *
	 * @param response
	 * @param resultReturn
	 * @throws IOException
	 * @author yangShiXiong
	 * @Data 2020年12月21日
	 */
    public void writeToWeb(HttpServletResponse response, ResultReturn<?> resultReturn) throws IOException {
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(resultReturn));
        out.flush();
        out.close();
    }
}
