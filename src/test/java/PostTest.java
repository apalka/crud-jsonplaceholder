import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class PostTest extends BaseTest{

    @BeforeAll
    static void beforeAll() {
        baseBeforeAll();
    }

    @BeforeEach
    void beforeEach() {
        baseBeforeEach();
    }

    @Test
    public void createNewPhoto() {

        JSONObject photo = new JSONObject();
        photo.put("albumId", fakeAlbumId);
        photo.put("title", fakeTitle);
        photo.put("url", fakeUrl);
        photo.put("thumbnailUrl", fakeThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .when()
                .post(BASE_URL + END_PHOTOS)
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
//        System.out.println(response.asString());

        Assertions.assertEquals(fakeAlbumId, json.get("albumId"));
        Assertions.assertEquals(fakeTitle, json.get("title"));
        Assertions.assertEquals(fakeUrl, json.get("url"));
        Assertions.assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));
        Assertions.assertEquals("5001", json.get("id").toString());

    }
}
