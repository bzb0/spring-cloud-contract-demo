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
package com.bzb.cloud.contract.producer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"isbn"})
@ToString
public class Book {

  @Id
  private String isbn;

  @Column
  @NotNull
  private String name;

  @Column
  @NotNull
  private String publisher;

  @Column
  private int publishedYear;

  public Book() {
  }

  public void updateBook(Book other) {
    this.name = other.getName();
    this.publisher = other.getPublisher();
    this.publishedYear = other.getPublishedYear();
  }
}
