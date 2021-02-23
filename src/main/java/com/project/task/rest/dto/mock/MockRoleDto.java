package com.project.task.rest.dto.mock;

import com.project.task.rest.dto.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MockRoleDto extends BaseDto {

    private List<MockRoleDtoWrapper> role;

}
