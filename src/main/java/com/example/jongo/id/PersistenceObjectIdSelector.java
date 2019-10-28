package com.example.jongo.id;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.jongo.marshall.jackson.JacksonObjectIdUpdater;

public class PersistenceObjectIdSelector extends JacksonObjectIdUpdater.BeanPropertyDefinitionIdSelector {

  @Override
  public boolean isId(BeanPropertyDefinition property) {
    return isIdAnnotated(property) || super.isId(property);
  }

  @Override
  public boolean isObjectId(BeanPropertyDefinition property) {
    return isIdAnnotated(property) || super.isObjectId(property);
  }

  private boolean isIdAnnotated(BeanPropertyDefinition property) {
    return property != null &&
      property.getPrimaryMember() != null &&
      property.getPrimaryMember().getAnnotation(javax.persistence.Id.class) != null;
  }

}
