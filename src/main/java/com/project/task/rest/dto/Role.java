package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import com.project.task.rest.dto.base.BaseRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Role extends BaseDto {

    private String name, description;




    //private String perms;


}
