/*package com.ecommerce.app.filter;

import static com.ecommerce.app.constant.SecurityConstant.*;
import com.ecommerce.app.entity.HttpResponse;
import static org.springframework.http.HttpStatus.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setHttpStatusCode(FORBIDDEN.value());
        httpResponse.setHttpStatus(FORBIDDEN);
        httpResponse.setReason(FORBIDDEN.getReasonPhrase());
        httpResponse.setMessage(FORBIDDEN_MESSAGE);
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(outputStream , httpResponse);
        outputStream.flush();
    }
}

 */

