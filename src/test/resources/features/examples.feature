Feature: Book get,add,delete,update

  @smoke @get_book
  Scenario Outline: Get book from book list with parameters
    Given Get book information with "<key>","<value>"
    Then Verify "<statusCode>" response is successful
    Examples:
      | key   | value             | statusCode |
      | pages | 4412              | 200        |
      | title | The Divine Comedy | 200        |
      | year  | 1958              | 200        |
      | id    | 5                 | 200        |

  @smoke @add_book
  Scenario: Add book to book list successfully
    When Add new book with random data to book list
    Then Verify book is added

  @smoke @update_book
  Scenario Outline: Update book with parameters
    Given Update "<id>" id book information with "<pages>","<year>","<author>","<country>","<title>"
    Then Verify "<statusCode>" response is successful
    Examples:
      | id | pages | year | author         | country | title         | statusCode |
      | 2  | 1526  | 1906 | Updated Author | Nigeria | updated Title | 200        |
      | 3  |       |      | Updated Author | Nigeria | updated Title | 200        |

  @smoke
  Scenario Outline: Get all books from the API
    Given get books to "<url>"
    Then Verify "<statusCode>" response is successful
    Examples:
      | url      | statusCode |
      | /books | 200        |

  @smoke
  Scenario: Delete book with id
    Given Delete last added book.
    Then Verify "200" response is successful



