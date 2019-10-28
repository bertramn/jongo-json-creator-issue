package com.example.jongo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @see Session
 */
@JsonPropertyOrder({
  "type",
  "id",
  "name",
  "sessions"
})
public interface Transaction {

  @NotNull
  String getId();

  @NotNull
  String getName();

  @NotNull
  List<Session> getSessions();

}
