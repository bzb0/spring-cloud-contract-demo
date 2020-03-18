package booksapi

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description("When a GET request with ISBN '1-56619-909-3' is made, the corresponding Book is returned")
  request {
    method 'GET'
    url '/books/1-56619-909-3'
  }
  response {
    status 200
    body(
        isbn: "1-56619-909-3",
        name: "Spring Cloud Contract",
        publisher: "Publicis",
        publishedYear: "2020"
    )
    headers {
      contentType(applicationJson())
    }
  }
}
