package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseErrorEntity extends BaseDto {

    private int errorCode = 0;

    private String message = "Error message";

}
