package com.example.jongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
public interface Session {

  @NotNull
  String getId();

  @NotNull
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  String getType();

}
