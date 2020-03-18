package booksapi

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description("When a PUT request with a Book with ISBN '1-56619-909-3' is made, the updated Book data is returned")
  request {
    method 'PUT'
    url '/books/1-56619-909-3'
    body(
        name: "Java Programming",
        publisher: "Springer",
        publishedYear: "2019"
    )
    headers {
      contentType(applicationJson())
    }
  }
  response {
    status 200
    body(
        isbn: "1-56619-909-3",
        name: "Java Programming",
        publisher: "Springer",
        publishedYear: "2019"
    )
    headers {
      contentType(applicationJson())
    }
  }
}
