package security.coreSpringSecurity.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 사용자가 인증을 성공한 이후에 자원에 접근하려고 하는데
 * 자원에 접근할 수 있는 자격, 권한이 없을 경우 또는 권한이 아닌 경우 인가 예외가 발생함.
 * 인가 예외는 인증 필터가 처리하는 것이 아니고, 인가 예외를 처리하는 ExceptionTranslationFilter가 있음.
 * 인가 예외는 FilterSecurityInterceptor가 권한을 심사하여 인가 예외를 발생시키고
 * FilterSecurityInterceptor가 던진 예외를 ExceptionTranslationFilter가 받음.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    private String errorPage;

    /**
     *
     * @param request
     * @param response
     * @param accessDeniedException 인가 예외
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String deniedUrl = errorPage + "?exception=" + accessDeniedException.getMessage();
        response.sendRedirect(deniedUrl);
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}







