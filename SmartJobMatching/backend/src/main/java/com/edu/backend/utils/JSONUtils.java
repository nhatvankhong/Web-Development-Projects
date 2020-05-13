package com.edu.backend.utils;

import java.io.IOException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtils {

    public static String toJson(final Object instance) {
    	ObjectMapper mapper = new ObjectMapper();
        String response = null;
        try {
            response = mapper.writeValueAsString(instance);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
			e.printStackTrace();
		}
        return response;
    }

    public static <T> T fromJson(final String json, final Class<T> clazz) {
    	ObjectMapper mapper = new ObjectMapper();
        T response = null;
        try {
            response = mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}