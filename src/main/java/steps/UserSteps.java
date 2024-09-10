package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static constants.Url.*;
import static io.restassured.RestAssured.given;
import model.User;

public class UserSteps {

    @Step("Создание уникального пользователя")
    public ValidatableResponse createUser(User user){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(CREATE_USER_ENDPOINT)
                .then();
    }

    @Step("Логин юзера в системе")
    public ValidatableResponse loginUser(User user){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(LOGIN_ENDPOINT)
                .then();
    }

    @Step("Удаление юзера")
    public ValidatableResponse deleteUser(String accessToken){
        return given()
                .baseUri(BASE_URI)
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .when()
                .delete(USER_ENDPOINT)
                .then();
    }

}
