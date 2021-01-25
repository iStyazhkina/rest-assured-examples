package tests;

import filter.CustomFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static filter.CustomFilter.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static utils.TestUtils.*;
import static utils.TestUtils.getRandomEmail;

public class DemoWebShopTests {

    private static RequestSpecification requestSpec = given()
            .baseUri("http://demowebshop.tricentis.com/")
            .filter(CUSTOM_ALLURE_REST);

    @Test
    @DisplayName("Add item to wish list")
    void shouldAddToWishList() {
        requestSpec
                    .body(readFromFile("src/test/resources/wishList.txt"))
                .when()
                    .post("addproducttocart/details/80/2")
                .then()
                    .statusCode(200)
                    .body("success", is(true));
    }

    @Test
    @DisplayName("Subscribe to the newsletter")
    void shouldSubscribeToTheNewsLetter() {
        requestSpec
                    .body("email=" + getRandomEmail())
                    .contentType(ContentType.URLENC)
                .when()
                    .post("subscribenewsletter")
                .then()
                    .statusCode(200)
                    .body("Success", is(true))
                    .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }
}
