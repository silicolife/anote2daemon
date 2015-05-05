package com.silicolife.anote2daemon.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

import com.silicolife.anote2daemon.model.core.entities.CustomSpringUser;
import com.silicolife.anote2daemon.utils.ApplicationContextUtils;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.anote2daemon.webservice.HibernateAwareObjectMapper;

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
		AuthUsers user = userLogged(authentication);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.addDateHeader("Date", new Date().getTime());
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);

		user.setAuPassword(null);
		DaemonResponse<AuthUsers> responseObj = new DaemonResponse<AuthUsers>(user);
		HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();
		final byte[] data = mapper.writeValueAsBytes(responseObj);
		String json = new String(data);
		PrintWriter output = response.getWriter();
		output.print(json);
		output.flush();
		output.close();

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
	private AuthUsers userLogged(Authentication auth) {
		ApplicationContext v = ApplicationContextUtils.getApplicationContext();
		AuthUsers user = null;
		if (auth.isAuthenticated()) {
			CustomSpringUser customUser = (CustomSpringUser) auth.getPrincipal();
			user = customUser.getRepositoryUser();
			((UsersLogged) v.getBean("usersLogged")).setCurrentUserLogged(user);
		} else {
			((UsersLogged) v.getBean("usersLogged")).setCurrentUserLogged(null);
		}

		return user;
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
