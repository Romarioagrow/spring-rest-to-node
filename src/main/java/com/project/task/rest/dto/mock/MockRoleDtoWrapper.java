package com.project.task.rest.dto.mock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MockRoleDtoWrapper {

    String name, description;

    List<MockRoleParamsDto> perms;
}
