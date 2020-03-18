package booksapi

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    description("When a POST request with a Book is made, the created Book's ISBN is returned")
    method POST()
    url '/books'
    body(
        isbn: "978-1-56619-909-4",
        name: "Hello World",
        publisher: "Publicis",
        publishedYear: "2020"
    )
    headers {
      contentType(applicationJson())
    }
  }
  response {
    status CREATED()
    body("978-1-56619-909-4")
    headers {
      contentType(textPlain())
    }
  }
}
