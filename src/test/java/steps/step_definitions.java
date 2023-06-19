package steps;
import helpers.helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.json.simple.JSONObject;
import java.util.List;

public class step_definitions extends helpers {
    private final String BASE_URL = "http://localhost:3000";
    private final String path = "/books";
    private Response response;

    @When("Add new book with random data to book list")
    public void addNewBook() {
        RestAssured.baseURI = BASE_URL;
        JSONObject book = new_book();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(book.toJSONString()).post(path);
    }

    @Then("Verify book is added")
    public void bookIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
        response.then().statusCode(201).log().body();
    }

    @Given("get books to {string}")
    public void getBooksToUrl(String url) throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.when().get(path);
    }

    @Given("Get book information with {string},{string}")
    public void getBookInfo(String key, String value) throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam(key, value);
        response = request.when().get(path);
    }

    @When("Update {string} id book information with {string},{string},{string},{string},{string}")
    public void updateBook(String id, String pages, String year, String author, String country, String title) throws Exception {
        RestAssured.baseURI = BASE_URL;
        JSONObject updatedBook = update_book(pages, year, author, country, title);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(updatedBook.toJSONString()).put(path + "/" + id);
    }

    @When("Delete last added book.")
    public void deleteBook() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.delete(path + "/" + get_last_item_id());
        response.then().log().body();
    }

    @Then("Verify {string} response is successful")
    public void responseIsStatusCode(String statusCode) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(Integer.parseInt(statusCode), responseCode);
        response.then().log().body();
    }

    public int get_last_item_id() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.when().get(path);
        List<Integer> idList = response.jsonPath().getList("id");
        int lastItemId = idList.get(idList.size() - 1);
        return lastItemId;
    }
}