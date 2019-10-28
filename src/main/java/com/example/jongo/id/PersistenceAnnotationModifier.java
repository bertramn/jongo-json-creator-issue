package com.example.jongo.id;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import org.jongo.marshall.jackson.JongoAnnotationIntrospector;
import org.jongo.marshall.jackson.configuration.MapperModifier;

public class PersistenceAnnotationModifier implements MapperModifier {

  @Override
  public void modify(ObjectMapper mapper) {
    AnnotationIntrospector jongoIntrospector = new JongoAnnotationIntrospector(new PersistenceAnnotationIdSelector());
    AnnotationIntrospector jacksonIntrospector = mapper.getSerializationConfig().getAnnotationIntrospector();
    mapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(jongoIntrospector, jacksonIntrospector));
  }

}
