package com.example.jongo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

public abstract class AbstractTransaction implements Transaction {

  @Id
  @GeneratedValue
  private String id;

  private String name;

  private final List<Session> sessions = new ArrayList<>(2);

  protected AbstractTransaction(@NotNull String id,
                                @NotNull String name,
                                @Nullable Session... sessions) {
    this.id = id;
    this.name = name;
    if (sessions != null && sessions.length > 0) {
      this.sessions.addAll(Arrays.asList(sessions));
    }
  }

  @NotNull
  @Override
  public String getId() {
    return id;
  }

  @NotNull
  @Override
  public String getName() {
    return name;
  }

  @NotNull
  public List<Session> getSessions() {
    return Collections.unmodifiableList(sessions);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractTransaction that = (AbstractTransaction) o;
    return id.equals(that.id) &&
      name.equals(that.name) &&
      sessions.equals(that.sessions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, sessions);
  }

  @Override
  public String toString() {
    return new StringJoiner(" ", "transaction ", "")
      .add("id=" + id)
      .add("name=" + name)
      .add("sessions=" + sessions)
      .toString();
  }

}
