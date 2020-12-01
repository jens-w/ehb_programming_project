package com.brielage.coursequiz.controllers;

import com.brielage.coursequiz.domain.Student;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.services.StudentService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/user")
public class UserRestController {
    private StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    // is this how we do it?
    // apparently yes, after more googling and testing
    @PostMapping(value = "/something",
            consumes = "application/json",
            produces = "application/json")
    public Map something(@RequestBody JsonNode jsonNode) {
        logger.info(jsonNode.toPrettyString());
        logger.info("\n\n");
        logger.info(jsonNode.elements().toString());
        logger.info("\n\n");

        Iterator<String> fieldNames = jsonNode.fieldNames();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();


            JsonNode field = jsonNode.get(fieldName);
            logger.info(field.toPrettyString());
        }

        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> name = fields.next();

            logger.info("\n\n\n");
            logger.info("key: " + name.getKey());
            logger.info("value: " + name.getValue());

            if (name.getValue().isInt()) logger.info("yay");
        }

        // making the response JSON data
        ArrayList test = new ArrayList();
        test.add("key1");
        test.add("val1");
        test.add("key2");
        test.add("val2");

        // return Map, will be the JSON response
        return Map.of("key0", test, "nogeenkey", "THIS IS A REPLY");
    }

    @GetMapping
    public void somethingElse() {
        User u = studentService.findById(1).get();
        logger.info(u.toString());
    }
}
