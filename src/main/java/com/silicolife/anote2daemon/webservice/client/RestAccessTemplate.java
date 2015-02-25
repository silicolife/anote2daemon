package com.silicolife.anote2daemon.webservice.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestAccessTemplate extends RestTemplate {

	private final CloseableHttpClient httpClient;
	private final CookieStore cookieStore;
	private final HttpClientContext httpContext;
	private final RestHttpComponentsClientHttpRequestFactory statefullHttpComponentsClientHttpRequestFactory;
	private final List<HttpMessageConverter<?>> messageConverters;

	public RestAccessTemplate() {
		messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new RestMappingJacksonHttpMessageConverter());
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		httpClient = HttpClients.createDefault();
		httpContext = HttpClientContext.create();
		cookieStore = new BasicCookieStore();
		httpContext.setCookieStore(cookieStore);
		statefullHttpComponentsClientHttpRequestFactory = new RestHttpComponentsClientHttpRequestFactory(httpClient, httpContext);
		super.setMessageConverters(messageConverters);
		super.setErrorHandler(new MyResponseErrorHandler());
		super.setRequestFactory(statefullHttpComponentsClientHttpRequestFactory);
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public CloseableHttpClient getCloseableHttpClient() {
		return httpClient;
	}

	public HttpClientContext getHttpClientContext() {
		return httpContext;
	}

	public RestHttpComponentsClientHttpRequestFactory getStatefulHttpClientRequestFactory() {
		return statefullHttpComponentsClientHttpRequestFactory;
	}
}