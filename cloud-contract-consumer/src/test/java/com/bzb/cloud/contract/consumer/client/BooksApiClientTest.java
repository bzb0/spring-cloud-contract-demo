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
package com.bzb.cloud.contract.consumer.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.bzb.cloud.contract.consumer.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.bzb.spring.cloud:cloud-contract-producer:0.0.1-SNAPSHOT:stubs:8001",
    stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class BooksApiClientTest {

  @Autowired
  private BooksApiClient booksApiClient;

  @Test
  public void clientShouldReturnBookForInputISBN() {
    String isbn = "1-56619-909-3";
    Book book = booksApiClient.getBook(isbn);

    assertThat(book.getIsbn()).isEqualTo(isbn);
    assertThat(book.getName()).isEqualTo("Spring Cloud Contract");
    assertThat(book.getPublisher()).isEqualTo("Publicis");
    assertThat(book.getPublishedYear()).isEqualTo(2020);
  }

  @Test
  public void clientShouldCreateABookAndReturnISBN() {
    Book book = new Book("978-1-56619-909-4", "Hello World", "Publicis", 2020);
    String bookIsbn = booksApiClient.createBook(book);

    assertThat(bookIsbn).isEqualTo(book.getIsbn());
  }

  @Test
  public void clientShouldUpdateBookAnExistingBook() {
    String isbn = "1-56619-909-3";
    Book book = new Book(isbn, "Java Programming", "Springer", 2019);
    Book updatedBook = booksApiClient.updateBook(isbn, book);

    assertThat(updatedBook.getIsbn()).isEqualTo(isbn);
    assertThat(updatedBook.getName()).isEqualTo(book.getName());
    assertThat(updatedBook.getPublisher()).isEqualTo(book.getPublisher());
    assertThat(updatedBook.getPublishedYear()).isEqualTo(book.getPublishedYear());
  }
}
