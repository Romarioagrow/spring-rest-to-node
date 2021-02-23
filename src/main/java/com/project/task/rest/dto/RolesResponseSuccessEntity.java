package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class RolesResponseSuccessEntity extends BaseDto {

    private Set<Role> roleSet;

}
