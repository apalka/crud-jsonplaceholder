import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class DeleteTest extends BaseTest {

    @Test
    public void deletePhoto() {

        Response response = given()
                .pathParam("photoId", "1")
                .when()
                .delete(BASE_URL + END_PHOTOS + "/{photoId}")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}