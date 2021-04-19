package com.example.fda.demo.exception.integration;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {

    switch (response.status()) {
      case 400:
        log.error("Status code " + response.status() + ", methodKey = " + methodKey);
      case 404:
        {
          log.error(
              "Error when using Feign client to send HTTP Request. Status code "
                  + response.status()
                  + ", methodKey = "
                  + methodKey);
          return new ResponseStatusException(
              HttpStatus.valueOf(response.status()), response.body().toString());
        }
      default:
        return new Exception(response.reason());
    }
  }
}
