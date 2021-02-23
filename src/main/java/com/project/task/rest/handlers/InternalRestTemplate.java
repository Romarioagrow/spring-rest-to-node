package com.project.task.rest.handlers;

import com.project.task.rest.dto.AccessRequest;
import com.project.task.rest.dto.AccessResponseSuccessEntity;
import com.project.task.rest.dto.ResponseErrorEntity;
import com.project.task.rest.dto.Role;
import com.project.task.rest.dto.mock.MockRoleDto;
import com.project.task.rest.dto.response.RoleResponseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Service
public class InternalRestTemplate {

    @Autowired
    public InternalRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }
    private static RestTemplate restTemplate;

    private final String GET_ROLES_MOCK_URL = "http://localhost:5000/authz/roles/ns/com.att.fortyportal";

    private final String ADD_USER_ROLE_URL = "http://localhost:5000/authz/userRole";

    public ResponseEntity<?> getRolesFromMock() {
        try {
            ResponseEntity<MockRoleDto> response = restTemplate.getForEntity(GET_ROLES_MOCK_URL, MockRoleDto.class);
            HttpStatus httpStatus = response.getStatusCode();
            return new ResponseEntity<>(mapBodyFromMock(response), httpStatus);
        }
        catch (HttpClientErrorException | HttpServerErrorException exception) {
            return handleHttpErrorResponse(exception);
        }
    }

    private ResponseEntity<?> handleHttpErrorResponse(HttpStatusCodeException exception) {
        HttpStatus statusCode = exception.getStatusCode();
        log.warn("getRolesFromMock Http Status {}", statusCode);
        return new ResponseEntity<>(new ResponseErrorEntity(), statusCode);
    }

    public ResponseEntity<?> requestNewRole(AccessRequest params) {
        try {
            log.info(params.toString());

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(ADD_USER_ROLE_URL, params, String.class);

            HttpStatus statusCode = responseEntity.getStatusCode();
            String responseBody = responseEntity.getBody();

            log.info(responseEntity.toString());
            log.info(responseBody);
            return new ResponseEntity<>(mapResponseBodySuccess(params), statusCode);
         }
        catch (HttpClientErrorException | HttpServerErrorException exception) {
            return handleHttpErrorResponse(exception);
        }
    }

    private RoleResponseDto mapBodyFromMock(ResponseEntity<MockRoleDto> body) {
        List<Role> roles = new ArrayList<>();
        RoleResponseDto roleResponseDto = new RoleResponseDto();

        MockRoleDto mockRoleDto = body.getBody();
        if (mockRoleDto == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        mockRoleDto.getRole().forEach(mockRoleDtoWrapper -> {
            roles.add(new Role(mockRoleDtoWrapper.getName(), mockRoleDtoWrapper.getDescription()));
        });

        roleResponseDto.setRoles(roles);

        return roleResponseDto;
    }

    private AccessResponseSuccessEntity mapResponseBodySuccess(AccessRequest params) {
        return new AccessResponseSuccessEntity(params.getUser().getName());
    }

}
