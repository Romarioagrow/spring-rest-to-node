package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import com.project.task.rest.dto.base.BaseRoleDto;
import lombok.Data;

@Data
public class User extends BaseDto {
    private String name, description;

}
