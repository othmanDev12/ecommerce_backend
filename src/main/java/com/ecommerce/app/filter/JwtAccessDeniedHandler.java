/* package com.ecommerce.app.filter;

import com.ecommerce.app.entity.HttpResponse;

import static com.ecommerce.app.constant.SecurityConstant.*;
import static  org.springframework.http.HttpStatus.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setHttpStatusCode(UNAUTHORIZED.value());
        httpResponse.setHttpStatus(UNAUTHORIZED);
        httpResponse.setReason(UNAUTHORIZED.getReasonPhrase());
        httpResponse.setMessage(ACCESS_DENIED_MESSAGE);

        OutputStream outputStream = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(outputStream , httpResponse);

        outputStream.flush();
    }
}


 */
