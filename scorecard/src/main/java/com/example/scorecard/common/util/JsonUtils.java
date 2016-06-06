package com.example.scorecard.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




/**
 * The Class JsonUtils.
 */
public class JsonUtils {
	
	private static ObjectMapper mapper_ = new ObjectMapper();
	
	/**
	 * Convert object to json stream.
	 *
	 * @param object the object
	 * @return the byte[]
	 * @throws JsonProcessingException the json processing exception
	 */
	public static byte[] convertObjectToJsonStream(Object object) throws JsonProcessingException {
		return mapper_.writeValueAsBytes(object);
	}
	
	/**
	 * Convert object to json string.
	 *
	 * @param object the object
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	public static String convertObjectToJsonString(Object object) throws JsonProcessingException {
		return mapper_.writeValueAsString(object);
	}

	/**
	 * Convert json string to object.
	 *
	 * @param <T> the generic type
	 * @param jsonStr the json str
	 * @param classType the class type
	 * @return the t
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <T> T convertJsonStringToObject(String jsonStr, Class<T> classType) throws IOException {
		return mapper_.readValue(jsonStr, classType);
	}
	
	public static ObjectMapper getMapper() {
		return mapper_;
	}
	
}
