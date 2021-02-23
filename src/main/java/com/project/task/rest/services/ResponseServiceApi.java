package com.project.task.rest.services;

import com.project.task.rest.dto.AccessRequest;
import org.springframework.http.ResponseEntity;

public interface ResponseServiceApi {

    ResponseEntity<?> getRoles();

    ResponseEntity<?>  requestNewRole(AccessRequest params);

}
