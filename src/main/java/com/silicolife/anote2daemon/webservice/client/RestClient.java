package com.silicolife.anote2daemon.webservice.client;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.cookie.Cookie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

public class RestClient {

	private final HttpHost targetHost = new HttpHost("127.0.0.1", 8443, "https");
	private final String targetRelativePath = "anote2daemon";
	private final String login = targetHost.toURI() + "/" + targetRelativePath + "/j_spring_security_check";
	private final String logout = targetHost.toURI() + "/" + targetRelativePath + "/j_spring_security_logout";
	private final RestAccessTemplate template;
	private final ParameterizedTypeReference<DaemonResponse<Object>> classReturned = new ParameterizedTypeReference<DaemonResponse<Object>>() {
	};
	private static RestClient instance = null;

	private RestClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		template = new RestAccessTemplate();
	}

	public static RestClient getInstance() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		if (instance == null) {
			instance = new RestClient();

		}
		return instance;
	}

	public RestAccessTemplate getTemplate() {
		return template;
	}

	public ResponseEntity<DaemonResponse<Object>> login(String username, String password) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		MultiValueMap<String, String> loginMap = new LinkedMultiValueMap<String, String>();
		loginMap.add("j_username", username);
		loginMap.add("j_password", password);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(loginMap, headers);
		ResponseEntity<DaemonResponse<Object>> response = template.exchange(login, HttpMethod.POST, request, classReturned);
		return response;
	}

	public ResponseEntity<DaemonResponse<Object>> logout() {
		ResponseEntity<DaemonResponse<Object>> response = template.exchange(logout, HttpMethod.POST, null, classReturned);
		return response;
	}

	public HttpHeaders getHeaderAfterLoginSuccesuful() {
		HttpHeaders requestHeaders = null;
		List<Cookie> cookies = template.getCookieStore().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					requestHeaders = new HttpHeaders();
					requestHeaders.add("Cookie", "JSESSIONID=" + cookie.getValue());
				}
			}
		}
		return requestHeaders;
	}
}