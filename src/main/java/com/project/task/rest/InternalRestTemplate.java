package com.project.task.rest;

import com.project.task.rest.dto.AccessRequest;
import com.project.task.rest.dto.AccessResponseSuccessEntity;
import com.project.task.rest.dto.ResponseErrorEntity;
import com.project.task.rest.dto.Role;
import com.project.task.rest.dto.mock.MockRoleDto;
import com.project.task.rest.dto.response.RoleResponseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Service
public class InternalRestTemplate {

    private static final RestTemplate restTemplate = new RestTemplate();

    private final String GET_ROLES_MOCK_URL = "http://localhost:5000/authz/roles/ns/com.att.fortyportal";

    private final String ADD_USER_ROLE_URL = "http://localhost:5000/authz/userRole";

    public ResponseEntity<?> getRolesFromMock() {

        ResponseEntity<MockRoleDto> response = restTemplate.getForEntity(GET_ROLES_MOCK_URL, MockRoleDto.class);

        int httpStatus = response.getStatusCodeValue();

        return switch (httpStatus) {
            case 200 -> new ResponseEntity<>(mapBodyFromMock(response), HttpStatus.OK);
            case 500 -> new ResponseEntity<>(new ResponseErrorEntity(), HttpStatus.INTERNAL_SERVER_ERROR);
            default -> new ResponseEntity<>(HttpStatus.NO_CONTENT);
        };

    }

    private RoleResponseDto mapBodyFromMock(ResponseEntity<MockRoleDto> body) {
        MockRoleDto bodyBody = body.getBody();

        RoleResponseDto roleResponseDto = new RoleResponseDto();

        List<Role> roles = new ArrayList<>();

        bodyBody.getRole().forEach(mockRoleDtoWrapper -> {
            roles.add(new Role(mockRoleDtoWrapper.getName(), mockRoleDtoWrapper.getDescription()));
        });

        roleResponseDto.setRoles(roles);

        return roleResponseDto;
    }


    public ResponseEntity<?> requestNewRole(AccessRequest params) {
        log.info(params.toString());

        try {

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(ADD_USER_ROLE_URL, params, String.class);

            HttpStatus statusCode = responseEntity.getStatusCode();

            int httpStatus = responseEntity.getStatusCodeValue();

            String responseBody = responseEntity.getBody();

            log.info(responseEntity.toString());
            log.info(responseBody);

            return switch (httpStatus) {
                case 500, 403, 409 -> new ResponseEntity<>(new ResponseErrorEntity(), statusCode);
                case 201 -> new ResponseEntity<>(mapResponseBodySuccess(params), statusCode);
                default -> new ResponseEntity<>(HttpStatus.NO_CONTENT);
            };
         }
        catch (HttpClientErrorException e) {
            log.warn("HttpClientErrorException");
            e.getStatusCode();
            return new ResponseEntity<>(new ResponseErrorEntity(), e.getStatusCode());
        }
    }

    private AccessResponseSuccessEntity mapResponseBodySuccess(AccessRequest params) {

        return new AccessResponseSuccessEntity(params.getUser().getName());
    }

}
