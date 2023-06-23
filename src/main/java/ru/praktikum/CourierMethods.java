package ru.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierMethods {


    private static final String COURIER_PATH = "/api/v1/courier";
    private static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";

    @Step("Отправка запроса на создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Удаление созданного курьера")
    public static void deleteCourier() {
        Courier login = new Courier(Courier.getRandomCourier().getLogin(), Courier.getRandomCourier().getPassword());
        Response response =
                given()
                        .log().all()
                        .header("Content-type", "application/json")
                        .and()
                        .body(login)
                        .when()
                        .post(COURIER_LOGIN_PATH);
        String id = response.jsonPath().getString("id");
        given()
                .log().all()
                .when()
                .delete(COURIER_PATH+"/" + id)
                .then().log().all();
    }

    @Step("Отправка запроса на авторизацию с данными курьера")
    public ValidatableResponse loginWithCourier(Courier courier) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courier).
                when().
                post(COURIER_LOGIN_PATH).
                then().log().all();
    }
}
