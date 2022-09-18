package security.coreSpringSecurity.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import security.coreSpringSecurity.security.provider.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 개발자가 만든 CustomUserDetailsService 구현체를 통해서 인증처리를 하게 됨.
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * 스프링 시큐리티가 인증 처리를 할 떄 우리가 만든 provider를 참조하여 인증처리 함
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        // 정적 파일은 보안 필터을 거치지 않고 통과함
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .antMatchers("/","/users","user/login/**").permitAll()
            .antMatchers("/mypage").hasRole("USER")
            .antMatchers("/messages").hasRole("MANAGER")
            .antMatchers("/config").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .defaultSuccessUrl("/")
            .permitAll()
        ;
    }
}
