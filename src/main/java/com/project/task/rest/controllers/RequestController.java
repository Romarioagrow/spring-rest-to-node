package com.project.task.rest.controllers;

import com.project.task.rest.dto.AccessRequest;
import com.project.task.rest.dto.RolesResponseSuccessEntity;
import com.project.task.rest.services.ResponseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RequestController {
    private final ResponseService responseService;


    @GetMapping("/roles")
    public ResponseEntity<?> getRolesRequest() {
        log.info("/api/roles");

        return responseService.getRoles();

    }

    @PostMapping("/accessRequest")
    public ResponseEntity<?> accessRequest(@RequestBody AccessRequest param) {
        log.info("/api/accessRequest");

        return responseService.requestNewRole(param);

    }

}
