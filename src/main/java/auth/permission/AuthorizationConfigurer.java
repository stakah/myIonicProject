package auth.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.http.*;

import security.dao.PermissionDAO;
import security.entity.Permission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Classe que configura o WebSecurity, possibilitando requerer que o
 * usuário esteja logado para acessar qualquer usuário
 * 
 * @author Techne
 *
 */
@Configuration
@EnableWebSecurity
public class AuthorizationConfigurer extends WebSecurityConfigurerAdapter {
  
  /**
   * DAO de Permission
   */
  @Autowired
  private PermissionDAO permissionRepository;
  
  /**
   * Instância do authenticationProvider
   * 
   * @see auth.permission.AuthenticationConfigurer
   */
  @Autowired
  private AuthenticationConfigurer authenticationProvider;
  
  /**
   * Método que configura o SpringSecurite com o authenticationProvider,
   * responsável também pela criação da base inicial, caso não exista nenhuma
   * permissão cadastrada
   * 
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
    
    if(permissionRepository.count() == 0)
      authenticationProvider.createDatabase();
  }
  
  private HttpMethod method(String verb) {
    // null for any method
    return "ALL".equals(verb) ? null : HttpMethod.valueOf(verb);
  }
  
  /**
   * Configurações default para WebSecurity
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
    // post sem csrf
    http.csrf().disable();
    
    // session manager
    http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/index.html").and()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/index.html");
    
    // public
    String [] publics = {"/favicon.ico", "/index.html", "/views/login.view.html", "/public/**", "/plugins/**", "/components/**", "/js/**", "/css/**", "/img/**", "/i18n/**", "/views/error/**"};
		http.authorizeRequests().antMatchers(publics).permitAll();
    
    List<Permission> permissions = permissionRepository.findAll();
    for(Permission p : permissions) {
			http.authorizeRequests().antMatchers(method(p.getVerb()), p.getPath()).hasAuthority(p.getRole().getName());
    }
    
		// denyAll
		http.authorizeRequests().antMatchers("/**").denyAll();
    
    // login/logout
    http
		  .formLogin()
    		.loginProcessingUrl("/auth")
 		    .loginPage("/index.html")
    		.successHandler(successHandler())
    		.failureHandler(failureHandler())
  	.and()
		.logout()
		  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .invalidateHttpSession(true);
    
  }
  
  /**
   * Handler para sucesso de autorização
   */
  private AuthenticationSuccessHandler successHandler() {
    return new AuthenticationSuccessHandler() {
      @Override
      public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                                          Authentication authentication)
              throws IOException, ServletException {
        // do some logic here if you want something to be done whenever
        // the user successfully logs in.
        
        HttpSession session = req.getSession();
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());
        
        // req.getSession().setAttribute("username", username);
        
        // set our response to OK status
        resp.setStatus(HttpServletResponse.SC_OK);
        
        String roles = authUser.getAuthorities().toString().replaceFirst("\\[", "").replaceFirst("\\]", "");
        
        String str = String.format("{\"name\":\"%s\",\"id\":\"%s\",\"login\":\"%s\",\"roles\":\"%s\",\"root\":%s}",
                authUser.getUsername(), -1, authUser.getUsername(), roles,
                roles.contains(AuthenticationConfigurer.ROLE_ADMIN_NAME));
        resp.getOutputStream().print(str);
        resp.setHeader("Content-Type", "application/json");
        // {"name":"Administrator","id":"6C2B5EB7-AFE7-4A04-9CE2-F6B2E8BB7503","login":"admin","roles":"11111111-1111-1111-1111-111111111111,00000000-0000-0000-0000-000000000000","root":true}
        
      }
      
    };
  }
  
  /**
   * Handler para falha de autorização
   */
  private AuthenticationFailureHandler failureHandler() {
    return new AuthenticationFailureHandler() {
      @Override
      public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae)
              throws IOException, ServletException {
        hsr1.setStatus(HttpStatus.UNAUTHORIZED.value());
      }
    };
  }
  
}
