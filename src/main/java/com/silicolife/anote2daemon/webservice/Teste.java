package com.silicolife.anote2daemon.webservice;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import pt.uminho.anote2.datastructures.documents.query.Query;

import com.silicolife.anote2daemon.webservice.client.RestClient;

public class Teste {

	public static void main(String[] args) {

		RestClient client = RestClient.getInstance();
		client.login("admin", "admin");
		HttpHeaders sessinCookie = client.getHeaderAfterLoginSuccesuful();
		HttpEntity<?> requestEntity = new HttpEntity<Object>(sessinCookie);
		ResponseEntity<DaemonResponse<List<Query>>> a = client.getTemplate().exchange("http://localhost:8080/anote2daemon/queries/getAllQueries", HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<DaemonResponse<List<Query>>>() {
				});
		
		ResponseEntity<DaemonResponse<Object>> log = client.logout();

		// ResponseEntity<DaemonResponse> a = client.login("admin", "admin");

		// ResponseEntity<List<Query>> response = (ResponseEntity<List<Query>>)
		// client.executeGet("queries/getAllQueries");

		// List<Query> ad = (List)response.getBody();
		/*
		 * ObjectMapper mapper = new ObjectMapper();
		 * mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		 * 
		 * String a =
		 * "{ \"id\": 1,\"date\": 1411468973000,\"organism\": \"\",\"completeQuery\": \"\",\"name\": \"New Corpus\",\"notes\": \"\",\"properties\": {\"fromDate\": \"1900\",\"Created by\": \"Workflow Information Retrieval and Extraction (Basics) on date 2014-09-23 11:42:14\"},\"publicationsSize\": 413,\"availableAbstracts\": 0,\"publications\": null,\"documentRelevance\": {\"1\": \"irrelevant\",\"2\": \"relevant\"},\"queryOriginType\": {\"id\": 1,\"origin\": \"PUBMED\"},\"keywords\": \"maize AND drought\"}"
		 * ;
		 * 
		 * 
		 * Query userFromJSON = mapper.readValue(a, Query.class);
		 */
		int as = 5;
	}
}
