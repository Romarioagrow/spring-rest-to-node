package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import com.project.task.rest.dto.base.BaseRoleDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class RolesResponseSuccessEntity extends BaseDto {


    private Set<Role> roleSet;
    //private String name, description;


}
