package sid.org.biblio.front.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TwoFactorAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{   
	
	
	AuthenticationManager authenticationManager;
    public TwoFactorAuthenticationFilter(AuthenticationManager authenticationManager) {
    	  this.authenticationManager = authenticationManager;
          //idk why I have to do this, otherwise it's null
          super.setAuthenticationManager(authenticationManager);
	}

	@Override
    protected String obtainUsername(HttpServletRequest request)
    {
        String username = request.getParameter(getUsernameParameter());
        String plus = request.getParameter(getPasswordParameter());
        String combinedUsername = username + ":" +plus;
        return combinedUsername;
    }
}