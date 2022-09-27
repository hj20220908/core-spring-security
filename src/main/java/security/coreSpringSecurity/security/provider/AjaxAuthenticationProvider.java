package security.coreSpringSecurity.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import security.coreSpringSecurity.security.common.FormWebAuthenticationDetails;
import security.coreSpringSecurity.security.service.AccountContext;
import security.coreSpringSecurity.security.token.AjaxAuthenticationToken;

/**
 * Ajax 인증 검증
 * Form 인증 방식과 유사
 */
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 검증 단계
     * AjaxAuthenticationProvider 실질적으로 인증처리하는 클래스
     * AuthenticationManager 로부터 Provider 가 인증처리 할 수 있도록 위임을 받음
     * AuthenticationManager 로부터 authentication(인증객체)를 전달받음
     * authentication 안에는 사용자가 인증을 시도할 떄 입력했던 아이디랑 비밀번호 정보가 저장 되어있음.
     * authentication 정보를 가지고 UserDetailsService로부터 AccountContext 타입으로 반환받음
     * 패스워드 검증, 시크릿키 검증 후
     * 호출한 AuthenticationManager에게 정보를 return함
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 아이디 패스워드 검증
        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(loginId);

        if (!passwordEncoder.matches(password, accountContext.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // account 객체와 권한정보 반환
        return new AjaxAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
    }

    /**
     *
     * authentication 타입과 CustomAuthenticationProvider 타입의 토큰이 일치할 때
     * CustomAuthenticationProvider 인증을 처리하도록 함
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AjaxAuthenticationToken.class);
    }
}
