package sid.org.config;


  
  import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import
  org.springframework.security.config.annotation.method.configuration.
  EnableGlobalMethodSecurity; import
  org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.
  EnableWebSecurity; import
  org.springframework.security.config.annotation.web.configuration.
  WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
  
  
  
  
  
  
  @Configuration
  
  @EnableWebSecurity
  
  @EnableGlobalMethodSecurity(securedEnabled = true) 
  public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
  
 /**
	 * configuration de spring security
	 * 
	 * 
	 * 
	 */
	  @Autowired
	  CustomUserDetailService userDetailsService;
	  @Autowired
	    private AuthenticationEntryPoint authEntryPoint;
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	    }

		  @Override protected void configure(HttpSecurity http) throws Exception {
			  http
			  .csrf().disable();
			
		        http.authorizeRequests().anyRequest().authenticated();
		 
		        
		        http.httpBasic().authenticationEntryPoint(authEntryPoint);
              
		  
		  }
		  
		  
		
		
		  }
		 