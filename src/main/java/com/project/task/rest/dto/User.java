package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import lombok.Data;

@Data
public class User extends BaseDto {

    private String name, description;

}
