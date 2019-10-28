package com.example.jongo;

import com.example.jongo.id.PersistenceAnnotationModifier;
import com.example.jongo.id.PersistenceObjectIdSelector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.fares.bind.jackson.discovery.SubtypeDiscoveryModule;
import io.fares.junit.mongodb.MongoExtension;
import io.fares.junit.mongodb.MongoForAllExtension;
import org.bson.BsonDocument;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;
import org.jongo.marshall.jackson.JacksonObjectIdUpdater;
import org.jongo.marshall.jackson.configuration.Mapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

class TransactionPersistenceIntegrationTest {

  @RegisterExtension
  static MongoForAllExtension mongo = MongoForAllExtension.defaultMongo();

  private Jongo jongo;

  private MongoCollection transactionCollection;

  @BeforeEach
  void clearDatabase() {

    mongo.getMongoClient()
      .getDatabase(MongoExtension.UNIT_TEST_DB)
      .getCollection("TestTransaction")
      .deleteMany(new BsonDocument());

  }

  @BeforeEach
  void setupJongo() {

    final Mapping mapping = new Mapping.Builder()
      .addModifier(new PersistenceAnnotationModifier())
      .registerModule(new JavaTimeModule())
      .registerModule(new SubtypeDiscoveryModule("com.example"))
      .build();

    final ObjectMapper objectMapper = mapping.getObjectMapper();

    final Mapper jongoMapper = new JacksonMapper.Builder(objectMapper)
      .withObjectIdUpdater(new JacksonObjectIdUpdater(objectMapper, new PersistenceObjectIdSelector()))
      .build();

    jongo = new Jongo(mongo.getMongoClient().getDB(MongoExtension.UNIT_TEST_DB), jongoMapper);

    transactionCollection = jongo.getCollection("TestTransaction");
  }

  @Test
  void itShouldSaveAndFindTransaction() {
    TestTransaction transaction = new TestTransaction(ObjectId.get().toHexString(), "ut", null);
    transactionCollection.save(transaction);
    TestTransaction saved = transactionCollection.findOne("{_id:{$oid:#}}", transaction.getId()).as(TestTransaction.class);
    Assertions.assertEquals(transaction, saved);
  }

}
