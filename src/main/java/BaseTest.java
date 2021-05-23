import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTest {
    public String bookBody, isbn, aisle;
    public String getByIdURI = "/Library/GetBook.php?ID=";
    public String getByAuthorURI = "/Library/GetBook.php?AuthorName=";
    public String addBookURI = "/Library/Addbook.php";
    public String deleteBookURI = "/Library/DeleteBook.php";

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://216.10.245.166";
        Constants uniqueID = new Constants();
        isbn = uniqueID.getIsbn();
        aisle = uniqueID.getAisle();
        bookBody = "{\n" +
                "\"name\":\"Richie Rich\",\n" +
                "\"isbn\":\"" + isbn + "\",\n" +
                "\"aisle\":\"" + aisle + "\",\n" +
                "\"author\":\"Divya\"\n" +
                "}";
    }

    public Response addBook(String addUrl, String jsonBody) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(addUrl)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract().response();
        return response;
    }

    public Response getBook(String getUrl, String parameter) {
        Response response = given()
                .get(getUrl + parameter)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract().response();
        return response;
    }

    public Response deleteBook(String deleteUrl, String id) {
        String deleteId = "{\n" +
                "\"ID\" :" + id + "\n" +
                "}";
        Response response = given()
                .header("Content-type", "application/json")
                .body(deleteId)
                .and()
                .delete(deleteUrl)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract().response();
        return response;


    }

}
