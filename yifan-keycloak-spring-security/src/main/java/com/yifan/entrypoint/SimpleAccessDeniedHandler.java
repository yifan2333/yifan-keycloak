package com.yifan.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.yifan.result.ActionResult;
import com.yifan.result.ResultType;
import com.yifan.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

/** 
 * AccessDeniedException 主要是在用户在访问受保护资源时被拒绝而抛出的异常  403
 *
 * @author: wuyifan
 * @since: 2019年12月10日 下午3:13
 * @version 1.0
 */
@Slf4j
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("授权失败", accessDeniedException);

        ActionResult.Builder<String> builder = new ActionResult.Builder<>();
        builder.resultType(ResultType.NOT_PERMISSION);

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseUtils.responseJsonWriter(response, builder.build());
    }
}
