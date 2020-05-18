package sid.org.config;


  
  import org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Configuration; import
  org.springframework.http.HttpMethod; import
  org.springframework.security.authentication.AuthenticationManager; import
  org.springframework.security.config.annotation.method.configuration.
  EnableGlobalMethodSecurity; import
  org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.
  EnableWebSecurity; import
  org.springframework.security.config.annotation.web.configuration.
  WebSecurityConfigurerAdapter;
  
  
  
  
  
  
  @Configuration
  
  @EnableWebSecurity
  
  @EnableGlobalMethodSecurity(securedEnabled = true) public class
  ApiSecurityConfig extends WebSecurityConfigurerAdapter {
  
 /**
	 * configuration de spring security
	 */
		  @Override protected void configure(HttpSecurity http) throws Exception {
			  http
			  .csrf().disable()
		  .authorizeRequests()
		  .antMatchers("/css/**", "/js/**", "/webjars/**", "/books","/books/**", "/prets/**","/users", "/users/**" ).permitAll()
		  .anyRequest().authenticated() 
		
				;
		  
		  }
		  
		  
		  
		  }
		 