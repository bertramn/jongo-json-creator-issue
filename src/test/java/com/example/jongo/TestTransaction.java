package com.example.jongo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonTypeName(PackageVersion.VERSION + ".Transaction")
public class TestTransaction extends AbstractTransaction {

  @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
  public TestTransaction(@NotNull @JsonProperty(value = "id", required = true) String id,
                         @NotNull @JsonProperty(value = "name", required = true) String name,
                         @Nullable @JsonProperty(value = "sessions") Session... sessions) {
    super(id, name, sessions);
  }

}
