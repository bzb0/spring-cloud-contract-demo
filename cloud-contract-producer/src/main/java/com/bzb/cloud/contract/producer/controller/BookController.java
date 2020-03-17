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
package com.bzb.cloud.contract.producer.controller;

import com.bzb.cloud.contract.producer.entity.Book;
import com.bzb.cloud.contract.producer.repository.BookRepository;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

  private final BookRepository bookRepository;

  @Autowired
  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping(path = "/{isbn}")
  public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
    Optional<Book> book = bookRepository.findById(isbn);
    return book.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping
  public ResponseEntity<String> createBook(@RequestBody @Valid Book book) {
    Book savedBook = this.bookRepository.save(book);
    return ResponseEntity.status(201).body(savedBook.getIsbn());
  }

  @PutMapping(path = "/{isbn}")
  public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book, @PathVariable String isbn) {
    Optional<Book> bookOptional = bookRepository.findById(isbn);
    if(bookOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    Book dbBook = bookOptional.get();
    dbBook.updateBook(book);
    dbBook = bookRepository.save(dbBook);
    return ResponseEntity.ok(dbBook);
  }
}
