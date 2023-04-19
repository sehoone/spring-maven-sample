package com.sehoon.springmavensample.module.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * sample 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/api/sample")
public class SampleController {

    /**
     * {@code GET /api/sample/hello-world} : get hello-world text
     *
     * @param
     * @return
     */
    @GetMapping("/hello-world")
    public String helloWorld() {
        String word = "hello-world!!";
        return word;
    }

    /**
     * {@code GET /action_name} : echo
     *
     * @param
     * @return
     */
    @RequestMapping(value = "echo", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = {
            RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
    public ResponseEntity<?> echoBack1(@RequestBody(required = false) byte[] rawBody) {
        log.debug("echoBack start " + new String(rawBody));

        return ResponseEntity.status(HttpStatus.OK).body(new String(rawBody));
    }

}
