import com.github.javafaker.Faker;

public class BaseTest {

    static protected String BASE_URL = "https://jsonplaceholder.typicode.com/";
    static protected String END_PHOTOS = "photos";
    static protected String END_USERS = "users";

    protected static Faker faker;
    protected Integer fakeAlbumId;
    protected String fakeTitle;
    protected String fakeUrl;
    protected String fakeThumbnailUrl;

    public static void baseBeforeAll() {
        faker = new Faker();
    }

    public void baseBeforeEach() {
        fakeAlbumId = faker.number().randomDigit();
        fakeTitle = faker.lorem().sentence();
        fakeUrl = faker.internet().url();
        fakeThumbnailUrl = faker.internet().url();
    }
}