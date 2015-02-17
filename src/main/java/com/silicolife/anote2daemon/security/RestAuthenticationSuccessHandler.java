package com.silicolife.anote2daemon.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.entities.CustomSpringUser;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.utils.ApplicationContextUtils;

/**
 * This class is called when the authentication is success
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	@Autowired
	private ApplicationContext appContext;

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException,
			IOException {

		// -- Send to UsersLogged context object the user logged
		userLogged(authentication);

		final SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			clearAuthenticationAttributes(request);
			return;
		}
		final String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			clearAuthenticationAttributes(request);
			return;
		}

		clearAuthenticationAttributes(request);
	}

	/**
	 * send to UserLogged context object the user logged in the Authentication
	 * 
	 * 
	 * @param auth
	 */
	private void userLogged(Authentication auth) {
		ApplicationContext v = ApplicationContextUtils.getApplicationContext();
		if (auth.isAuthenticated()) {
			CustomSpringUser customUser = (CustomSpringUser) auth.getPrincipal();
			Users user = customUser.getRepositoryUser();
			((UsersLogged) v.getBean("userLogged")).setCurrentUserLogged(user);
		} else {
			((UsersLogged) v.getBean("userLogged")).setCurrentUserLogged(null);
		}
	}

	/**
	 * 
	 * Clear parameters saved in cache during the Authentication process
	 * 
	 * @param requestCache
	 */
	public void setRequestCache(final RequestCache requestCache) {
		this.requestCache = requestCache;
	}
}
