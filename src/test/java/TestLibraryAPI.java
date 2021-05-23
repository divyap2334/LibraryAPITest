import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLibraryAPI extends BaseTest {
    @Test
    public void testAddBook() {
        Response response = addBook(addBookURI, bookBody);
        Assert.assertEquals(response.jsonPath().getString("Msg"), "successfully added");
    }
    @Test
    public void testGetBookByID() {
        Response addRequest = addBook(addBookURI, bookBody);
        Response getRequest = getBook(getByIdURI, addRequest.jsonPath().getString("ID"));
        Assert.assertEquals(getRequest.jsonPath().getString("author[0]"), "Divya");
    }
    @Test
    public void testGetBookByAuthor() {
        addBook(addBookURI, bookBody);
        Response getRequest = getBook(getByAuthorURI, "Divya");
        Assert.assertEquals(getRequest.jsonPath().getString("book_name[14]"), "Richie Rich");
    }
    @Test
    public void testDeleteBook() {
        Response addRequest = addBook(addBookURI, bookBody);
        Response deleteRequest = deleteBook(deleteBookURI, addRequest.jsonPath().getString("ID"));
        Assert.assertEquals(deleteRequest.jsonPath().getString("msg"), "book is successfully deleted");
    }

}
