package com.example.jongo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.StringJoiner;

public class TestSession implements Session {

  private static final String SESSION_TYPE = PackageVersion.VERSION + ".TestSession";

  @Id
  @GeneratedValue
  private final String id;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public TestSession(@NotNull @JsonProperty(value = "id", required = true) String id) {
    this.id = id;
  }

  @Override
  public @NotNull String getId() {
    return id;
  }

  @Override
  public @NotNull String getType() {
    return SESSION_TYPE;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TestSession that = (TestSession) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(" ", "session ", "")
      .add("id='" + id + "'")
      .toString();
  }

}
