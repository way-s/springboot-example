package com.example.security.handle;

import com.alibaba.fastjson.JSON;
import com.example.entity.constants.Constants;
import com.example.entity.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: mxx
 * @Description: 没有访问权限的处理程序
 */
@Component
public class NoPermissionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(Result.fail(Constants.PERMISSION_ERROR.getMsg())));
        response.getWriter().flush();
    }
}
