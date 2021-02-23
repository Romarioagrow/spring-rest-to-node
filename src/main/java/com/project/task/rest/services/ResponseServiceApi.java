package com.project.task.rest.services;

import com.project.task.rest.dto.AccessRequest;
import com.project.task.rest.dto.RolesResponseSuccessEntity;
import org.springframework.http.ResponseEntity;

public interface ResponseServiceApi {

    ResponseEntity<?> getRoles();

    Object requestNewRole(AccessRequest params);

}
