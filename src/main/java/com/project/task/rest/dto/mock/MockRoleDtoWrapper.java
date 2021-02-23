package com.project.task.rest.dto.mock;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MockRoleDtoWrapper {

    private String name, description;

    private List<MockRoleParamsDto> perms;
}
