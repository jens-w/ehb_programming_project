package com.brielage.coursequiz.domain;

import com.brielage.coursequiz.restresponses.JsonResponse;
import com.brielage.coursequiz.restresponses.JsonUserResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ResponseLogger {
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(ResponseLogger.class.getName());
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void logJsonResponse(JsonUserResponse jsonUserResponse) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(jsonUserResponse));
        logger.info("\nresponse:\n" + jsonNode.toPrettyString());
    }

    public static void logJsonResponse(JsonResponse jsonResponse) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(jsonResponse));
        logger.info("\nresponse:\n" + jsonNode.toPrettyString());
    }
}
