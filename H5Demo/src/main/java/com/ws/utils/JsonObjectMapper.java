package com.ws.utils;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

public class JsonObjectMapper extends ObjectMapper {

	public JsonObjectMapper() {
		super();
		this.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		this.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		this.getSerializationConfig().withSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
		this.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

}
