package plarktmaatsInterceptors;

import java.util.Map;

import plarktmaatsAware.UserAware;
import plarktmaatsDomein.Beheerder;
import plarktmaatsDomein.Persoon;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class BeheerderInterceptor implements Interceptor {

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = actionInvocation.getInvocationContext().getSession();

		Persoon user = (Persoon) session.get("user");

		if (user == null) {
			return Action.LOGIN;
		} else if (user instanceof Beheerder) {
			Action action = (Action) actionInvocation.getAction();

			if (action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}

			return actionInvocation.invoke();
		} else {
			return Action.LOGIN;
		}
	}
}
