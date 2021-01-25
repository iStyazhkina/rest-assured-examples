package filter;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomFilter  {

    public static final AllureRestAssured CUSTOM_ALLURE_REST =
            new AllureRestAssured()
                    .setRequestTemplate("request.ftl")
                    .setResponseTemplate("response.ftl");
}