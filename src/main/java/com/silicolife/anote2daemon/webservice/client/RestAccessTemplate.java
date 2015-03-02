package com.silicolife.anote2daemon.webservice.client;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
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
	private final SSLContextBuilder builder;
	private final SSLConnectionSocketFactory sslsf;

	public RestAccessTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new RestMappingJacksonHttpMessageConverter());
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		sslsf = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
		httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
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