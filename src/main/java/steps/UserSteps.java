package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static constants.Url.*;
import static io.restassured.RestAssured.given;
import model.User;

public class UserSteps {

    @Step("Регистрация пользователя")
    public static Response createUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(BASE_URI +CREATE_USER_ENDPOINT);
        return response;
    }

    @Step("Вход пользователем")
    public static Response loginUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(BASE_URI +USER_ENDPOINT);
        return response;
    }

    @Step("Получение accessToken пользователя")
    public static String getAccessToken(User user) {
        return loginUser(user).then().extract().path("accessToken");
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        if (accessToken != null)
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(BASE_URI +USER_ENDPOINT);
    }

}
