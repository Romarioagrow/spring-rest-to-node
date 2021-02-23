package com.project.task.rest.dto;

import com.project.task.rest.dto.base.BaseDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccessRequest extends BaseDto {

    private String role;

    private User user;

}
