package helpers;
import org.json.simple.JSONObject;
import com.github.javafaker.Faker;

public class helpers {
    public static Faker faker = new Faker();
    public JSONObject new_book() {
        JSONObject book = new JSONObject();
        book.put("pages", faker.random().nextInt(50, 5000));
        book.put("year", faker.random().nextInt(1500, 2023));
        book.put("author", faker.name().fullName());
        book.put("country", faker.address().country());
        book.put("title", faker.name().title());
        return book;
    }

    public JSONObject update_book(String pages, String year, String author, String country, String title) {
        JSONObject book = new JSONObject();
        if (pages.length() == 0) pages = (faker.random().nextInt(50, 5000)).toString();
        if (year.length() == 0) year = (faker.random().nextInt(1500, 2023)).toString();
        if (author.length() == 0) author = faker.name().fullName();
        if (country.length() == 0) country = faker.address().country();
        if (title.length() == 0) country = faker.name().title();
        book.put("pages", pages);
        book.put("year", year);
        book.put("author", author);
        book.put("country", country);
        book.put("title", title);
        return book;
    }
}
