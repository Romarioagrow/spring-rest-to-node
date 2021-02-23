package com.project.task.rest.services;

import com.project.task.rest.handlers.InternalRestTemplate;
import com.project.task.rest.dto.AccessRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ResponseService implements ResponseServiceApi {
    private final InternalRestTemplate internalRestTemplate;



    @Override
    public ResponseEntity<?> getRoles() {
        log.info("processResponse");


        //RolesResponseSuccessEntity rolesFromMock = internalRestTemplate.getRolesFromMock();
        return internalRestTemplate.getRolesFromMock();



        //return new RolesResponseSuccessEntity();
        //return new RolesResponseSuccessEntity();


    }


    @Override
    public ResponseEntity<?> requestNewRole(AccessRequest params) {


        return internalRestTemplate.requestNewRole(params);



       // return null;
    }
}
