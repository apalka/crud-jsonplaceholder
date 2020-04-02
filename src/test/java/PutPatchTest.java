import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class PutPatchTest extends BaseTest {

    @BeforeAll
    static void beforeAll() {
        baseBeforeAll();
    }

    @BeforeEach
    void beforeEach() {
        baseBeforeEach();
    }

    @Test
    public void updatePhoto() {

        JSONObject photo = new JSONObject();
        photo.put("albumId", fakeAlbumId);
        photo.put("title", fakeTitle);
        photo.put("url", fakeUrl);
        photo.put("thumbnailUrl", fakeThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .pathParam("photoId", 1)
                .when()
                .put(BASE_URL + END_PHOTOS + "/{photoId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
//        System.out.println(response.asString());

        Assertions.assertEquals(fakeAlbumId, json.get("albumId"));
        Assertions.assertEquals(fakeTitle, json.get("title"));
        Assertions.assertEquals(fakeUrl, json.get("url"));
        Assertions.assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));
        Assertions.assertEquals("1", json.get("id").toString());

    }

    @Test
    public void updatePhotoFields() {

        JSONObject photo = new JSONObject();
        photo.put("title", fakeTitle);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .pathParam("photoId", 1)
                .when()
                .patch(BASE_URL + END_PHOTOS + "/{photoId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
//        System.out.println(response.asString());

        Assertions.assertEquals(fakeTitle, json.get("title"));
        Assertions.assertEquals("1", json.get("id").toString());
    }
}
