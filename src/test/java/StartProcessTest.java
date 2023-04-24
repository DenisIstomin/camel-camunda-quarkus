import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

@QuarkusTest
@QuarkusTestResource(PostgresResource.class)
public class StartProcessTest {

    final String patternUUID = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    @Test
    public void testStartProcessEndpoint() {
        given()
                .when().get("/start-process")
                .then()
                .statusCode(200)
                .body(matchesPattern(patternUUID));
    }
}
