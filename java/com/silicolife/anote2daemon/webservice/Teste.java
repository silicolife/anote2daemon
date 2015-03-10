package com.silicolife.anote2daemon.webservice;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Teste {

	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, JsonParseException, JsonMappingException, IOException {

	//	RestClient client = RestClient.getInstance();
	//	client.login("admin", "admin");
	//	HttpHeaders sessinCookie = client.getHeaderAfterLoginSuccesuful();
	//	HttpEntity<?> requestEntity = new HttpEntity<Object>(sessinCookie);
	//	ResponseEntity<DaemonResponse<List<Query>>> a = client.getTemplate().exchange("http://localhost:8080/anote2daemon/queries/getAllQueries", HttpMethod.GET, requestEntity,
	//			new ParameterizedTypeReference<DaemonResponse<List<Query>>>() {
	//			});

		//ResponseEntity<DaemonResponse<Object>> log = client.logout();
		//ResponseEntity<DaemonResponse<Object>> log2 = client.logout();
		// ResponseEntity<DaemonResponse> a = client.login("admin", "admin");

		// ResponseEntity<List<Query>> response = (ResponseEntity<List<Query>>)
		// client.executeGet("queries/getAllQueries");

		// List<Query> ad = (List)response.getBody();
		
		  ObjectMapper mapper = new ObjectMapper();
		  mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		 
		  String a =
		 "{ \"id\": 1,\"date\": 1411468973000,\"organism\": \"\",\"completeQuery\": \"\",\"name\": \"New Corpus\",\"notes\": \"\",\"properties\": {\"fromDate\": \"1900\",\"Created by\": \"Workflow Information Retrieval and Extraction (Basics) on date 2014-09-23 11:42:14\"},\"publicationsSize\": 413,\"availableAbstracts\": 0,\"publications\": null,\"documentRelevance\": {\"1\": \"irrelevant\",\"2\": \"relevant\"},\"queryOriginType\": {\"id\": 1,\"origin\": \"PUBMED\"},\"keyWords\": \"maize AND drought\"}"
		  ;
		 
		 
		  DaemonResponse<String> userFromJSON = mapper.readValue("{\"content\": \"123456789\",\"exception\": null}", new TypeReference<DaemonResponse<String>>() {});
		 
		int as = 5;
	}
}
