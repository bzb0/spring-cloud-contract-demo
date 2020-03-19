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

import com.bzb.cloud.contract.consumer.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BooksApiClient", url = "http://localhost:8001")
public interface BooksApiClient {

  @PostMapping(path = "/books")
  String createBook(@RequestBody Book book);

  @GetMapping(path = "/books/{isbn}")
  Book getBook(@PathVariable("isbn") String isbn);

  @PutMapping(path = "/books/{isbn}")
  Book updateBook(@PathVariable("isbn") String isbn, @RequestBody Book book);
}
