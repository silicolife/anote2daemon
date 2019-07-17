package com.silicolife.anote2daemon.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.silicolife.anote2daemon.webservice.HibernateAwareObjectMapper;

public class DaemonContentJsonSerializer<T> extends JsonSerializer<T>{

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		if(value instanceof Map) {
			gen.writeStartObject();
			for( Object key: ((Map) value).keySet()) 
				gen.writeStringField(recursiveMapKeysToJson(key, mapper), mapper.writeValueAsString(((Map) value).get(key)));
			gen.writeEndObject();
		} else if( value instanceof Pair) {
			List pair = new ArrayList<>();
			pair.add(((Pair)value).getLeft());
			pair.add(((Pair)value).getRight());
			gen.writeStartObject();
			gen.writeString(mapper.writeValueAsString(pair));
			gen.writeEndObject();
		} else {
			String json = mapper.writeValueAsString(value);
			gen.writeRawValue(json);
		}
	}
	
	private String recursiveMapKeysToJson(Object key, HibernateAwareObjectMapper mapper) throws JsonProcessingException {
		if(key instanceof Map) {
			StringBuffer sb = new StringBuffer();
			for(Object subkey: ((Map) key).keySet()) {
				if(sb.length() == 0) {
					sb.append("{");
				}else {
					sb.append(",");
				}
				sb.append(recursiveMapKeysToJson(subkey, mapper));
				sb.append(":");
				sb.append(mapper.writeValueAsString(((Map) key).get(subkey)));
			}
			sb.append("}");
			return sb.toString();
		}  else if( key instanceof Pair) {
			List pair = new ArrayList<>();
			pair.add(((Pair)key).getLeft());
			pair.add(((Pair)key).getRight());
			return mapper.writeValueAsString(pair);
		} else {
			return mapper.writeValueAsString(key);
		}
	}

}
