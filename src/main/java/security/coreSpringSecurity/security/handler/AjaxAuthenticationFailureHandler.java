package security.coreSpringSecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 인증 실패 시 예외 타입에 따라 메시지를 전송함.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errMsg = "Invalid Username or Password";

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 상황에 따라 다른 메시지를 호출함
        if (exception instanceof BadCredentialsException) {
            errMsg = "Invalid Username or Password";
        } else  if (exception instanceof DisabledException) {
            errMsg = "Locked";
        } else if (exception instanceof InsufficientAuthenticationException) {
            errMsg = "Invalid Secret Key";
        }

        objectMapper.writeValue(response.getWriter(), errMsg);
    }
}
