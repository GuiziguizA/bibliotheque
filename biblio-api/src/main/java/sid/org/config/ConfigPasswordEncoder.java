package sid.org.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

	/**
	 * 
	 * @author guali
	 *
	 */
	@Configuration
	public class ConfigPasswordEncoder {
		/**
		 * classe qui retourne un mot de passe crypte
		 * @return
		 */
		  @Bean
		  public PasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		  }

	}


