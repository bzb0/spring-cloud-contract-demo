/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bzb.cloud.contract.producer;

import com.bzb.cloud.contract.producer.controller.BookController;
import com.bzb.cloud.contract.producer.entity.Book;
import com.bzb.cloud.contract.producer.repository.BookRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Optional;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudContractProducerApplication.class)
public abstract class BooksApiBaseTest {

  @Autowired
  BookController bookController;

  @MockBean
  BookRepository bookRepository;

  @Before
  public void setUp() {
    Book saveBook = new Book("978-1-56619-909-4", "Hello World", "Publicis", 2020);
    Mockito.when(bookRepository.save(saveBook)).thenReturn(saveBook);

    String isbn = "1-56619-909-3";
    Book findBook = new Book(isbn, "Spring Cloud Contract", "Publicis", 2020);
    Mockito.when(bookRepository.findById(isbn)).thenReturn(Optional.of(findBook));

    Book updateBook = new Book(isbn, "Java Programming", "Springer", 2019);
    Mockito.when(bookRepository.save(updateBook)).thenReturn(updateBook);

    RestAssuredMockMvc.standaloneSetup(bookController);
  }
}
