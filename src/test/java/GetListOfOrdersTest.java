import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.OrderMethods;

import static org.hamcrest.Matchers.*;

@DisplayName("Тесты на получение списка заказов")
public class GetListOfOrdersTest {

    private final OrderMethods order = new OrderMethods();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void GetOrdersTest() {
        ValidatableResponse response = order.getOrders();
        response.assertThat().body("orders", not(emptyArray())).and().statusCode(200);
    }
}
