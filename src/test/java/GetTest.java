import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetTest extends BaseTest {

    @Test
    public void readAllPhotos() {
        Response response = given()
                .when()
                .get(BASE_URL + END_PHOTOS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<Integer> ids = json.getList("id");

        Assertions.assertEquals(5000, ids.size());
    }

    @Test
    public void readPhoto() {
        Response response = given()
                .when()
                .get(BASE_URL + END_PHOTOS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        //        System.out.println(response.asString());

        Assertions.assertTrue(json.get("title").toString().startsWith("accusamus beatae ad"));
        Assertions.assertEquals("https://via.placeholder.com/600/92c952", json.get("url"));
        Assertions.assertEquals("1", json.get("albumId").toString());

    }

    @Test
    public void readPhotosWithPathVariable() {
        Response response = given()
                .pathParam("photoId", 1)
                .when()
                .get(BASE_URL + END_PHOTOS + "/{photoId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertTrue(json.get("title").toString().startsWith("accusamus beatae ad"));
        Assertions.assertEquals("https://via.placeholder.com/600/92c952", json.get("url"));
        Assertions.assertEquals("1", json.get("albumId").toString());
    }

    @Test
    public void readPhotosWithQueryParam() {
        Response response = given()
                .queryParam("id", "1")
                .when()
                .get(BASE_URL + END_PHOTOS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertTrue(json.getList("title").get(0).toString().startsWith("accusamus beatae ad"));
        Assertions.assertEquals("https://via.placeholder.com/600/92c952", json.getList("url").get(0));
        Assertions.assertEquals("1", json.getList("albumId").get(0).toString());
    }
}