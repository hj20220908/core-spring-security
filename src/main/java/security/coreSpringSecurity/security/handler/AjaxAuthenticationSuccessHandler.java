package security.coreSpringSecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import security.coreSpringSecurity.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 인증에 성공한 정보를 얻을 수 있음
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // AjaxAuthenticationProvider 로직 참고 (최종적으로 성공한 인증 객체 Account를 저장하여 리턴했음)
        Account account = (Account) authentication.getPrincipal();

        // 클라이언트에 응답하기
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        // json 형식으로 변환 후 클라이언트에 전달
        objectMapper.writeValue(response.getWriter(), account);

    }

}
