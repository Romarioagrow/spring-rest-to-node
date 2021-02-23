package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessResponseSuccessEntity extends BaseDto {

    private String name;

}
