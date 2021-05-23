import com.github.javafaker.Faker;

public class Constants {
    Faker faker = new Faker();
    String isbn = faker.number().digits(5);
    String aisle = faker.number().digits(6);


    public String getAisle() {
        return aisle;
    }

    public String getIsbn() {
        return isbn;
    }
}
