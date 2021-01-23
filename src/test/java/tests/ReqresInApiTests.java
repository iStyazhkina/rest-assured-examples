package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import rest.Resource;
import rest.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static utils.TestUtils.*;

public class ReqresInApiTests {

    private static RequestSpecification requestSpec = given()
                .baseUri("https://reqres.in/")
                .filter(new AllureRestAssured());


    @Test
    @DisplayName("Delete user")
    void shouldDeleteUser() {
        requestSpec
                .when()
                    .delete("api/users/3")
                .then()
                    .statusCode(204);
    }

    /*
   Get body from File
    */

    @Test
    @DisplayName("Create user")
    void shouldCreateUser() {
        requestSpec
                    .body(getStringFromJsonFile("src/test/resources/newUser.json"))
                .when()
                    .post("api/users")
                .then()
                    .statusCode(201)
                    .body("id", notNullValue())
                    .body("createdAt", notNullValue());
    }

    @Test
    @DisplayName("Update user")
    void shouldUpdateUser() {
        requestSpec
                    .body(getStringFromJsonFile("src/test/resources/newUser.json"))
                .when()
                    .put("api/users/2")
                .then()
                    .statusCode(200)
                    .body("updatedAt", notNullValue());
    }


    /*
    Extract response to POJO
     */
    @Test
    @DisplayName("Check api returns single user")
    void shouldReturnSingleUser() {
        int userId = 5;

        User user = requestSpec
                .when()
                    .get("api/users/" + userId)
                .then()
                    .statusCode(200)
                    .extract()
                    .as(User.class);

        assertThat(user.getData().getId(), is(userId));
        assertThat(user.getData().getFirstName(), is("Charles"));
        assertThat(user.getData().getLastName(), is("Morris"));
        assertThat(user.getData().getEmail(), is("charles.morris@reqres.in"));
        assertThat(user.getData().getAvatar(), is("https://reqres.in/img/faces/5-image.jpg"));
    }

    @Test
    @DisplayName("Check api returns all resources")
    void shouldReturnListOfResources() {
        Resource resource = requestSpec.
                when()
                    .get("api/unknown")
                .then()
                    .statusCode(200)
                    .extract()
                    .as(Resource.class);

        Resource.Data firstResource = resource.getData().get(0);
        Resource.Data lastResource = resource.getData().get(5);

        assertThat(resource.getData().size(), is(6));
        assertThat(firstResource.getName(), is("cerulean"));
        assertThat(lastResource.getName(), is("blue turquoise"));
    }


}
