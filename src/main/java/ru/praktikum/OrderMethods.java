package ru.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderMethods {

    private static final String CREATE_ORDER_PATH = "/api/v1/orders";
    private static final String ORDER_CANCEL_PATH = "/api/v1/orders/cancel";

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH)
                .then().log().all();
    }

    @Step("Получение списка всех заказов")
    public ValidatableResponse getOrders() {
        return given()
                .log().all()
                .when()
                .get(CREATE_ORDER_PATH)
                .then().log().all();
    }

    @Step("Отмена заказа по трек-номеру '{trackNumber}'")
    public ValidatableResponse cancelOrder(int trackNumber) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .queryParam("track", trackNumber).
                when().
                put(ORDER_CANCEL_PATH).
                then().log().all();
    }
}
