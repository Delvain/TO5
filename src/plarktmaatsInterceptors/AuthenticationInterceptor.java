package plarktmaatsInterceptors;

import java.util.Map;

import plarktmaatsAware.UserAware;
import plarktmaatsDomein.Persoon;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class AuthenticationInterceptor implements Interceptor {

	public void destroy() {
	}

	public void init() {
	}

	public String intercept( ActionInvocation actionInvocation ) throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = actionInvocation.getInvocationContext().getSession();
		
		Persoon user = (Persoon) session.get("user" );
		
		if (user == null) {
		    return Action.LOGIN;
		} 
		else {
				
		    Action action = ( Action ) actionInvocation.getAction();
		    
		    if (action instanceof UserAware) {
		        ((UserAware)action).setUser(user);
		    }
		    
		    return actionInvocation.invoke();
		}
	}
}
