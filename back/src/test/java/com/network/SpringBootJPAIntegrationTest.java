// package com.network;

// import org.springframework.test.context.junit4.SpringRunner;
// import org.junit.runner.RunWith;
// import org.springframework.boot.test.context.SpringBootTest;

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = Application.class)
// public class SpringBootJPAIntegrationTest {

//     @Autowired
//     private GenericEntityRepository genericEntityRepository;

//     @Test
//     public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
//         GenericEntity genericEntity = genericEntityRepository
//           .save(new GenericEntity("test"));
//         GenericEntity foundEntity = genericEntityRepository
//           .findOne(genericEntity.getId());
 
//         assertNotNull(foundEntity);
//         assertEquals(genericEntity.getValue(), foundEntity.getValue());
//     }
// }