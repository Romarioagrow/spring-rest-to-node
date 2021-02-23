package com.project.task.rest.dto.mock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class MockRoleDtoObject {

    @JsonProperty
    String name, description;

    @JsonProperty
    List<Map<String, String>> perms;
    //Map<String, List< Map<String, String>>> perms;
}
