import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetUsersTest extends BaseTest {

    @Test
    public void checkEmailsDomains() {
        Response response = given()
                .when()
                .get(BASE_URL + END_USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");

        boolean polishDomainAddressOnList = emails.stream()
                .anyMatch(email -> email.endsWith("pl"));

        Assertions.assertFalse(polishDomainAddressOnList);
    }
}
