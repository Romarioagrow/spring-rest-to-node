package com.project.task.rest.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

        HttpStatus httpStatus = clientHttpResponse.getStatusCode();
        int httpErrorCode = clientHttpResponse.getRawStatusCode();

        if (clientHttpResponse.getStatusCode().is4xxClientError()) {
            log.warn("CLIENT_ERROR {}", httpErrorCode);
            throw new HttpClientErrorException(httpStatus);

        } else if (clientHttpResponse.getStatusCode().is5xxServerError()) {
            log.warn("SERVER_ERROR {}", httpErrorCode);
            throw new HttpServerErrorException(httpStatus);
        }
        else throw new IOException();
    }
}
