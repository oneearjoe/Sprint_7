import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.Courier;
import ru.praktikum.CourierMethods;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Негативные тесты на логин")
public class CourierLoginNegativeTest {

    Courier courier=Courier.getRandomCourier();
    static CourierMethods courierMethods = new CourierMethods();

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru";
    }
    @Test
    @DisplayName("Авторизация с  несуществующим логином")
    public void testErrorMessageForIncorrectLogin() {
        courier.setLogin("vader");
        ValidatableResponse response = courierMethods.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    public void testErrorMessageForNotFoundLogin() {
        courier.setLogin(null);
        ValidatableResponse response = courierMethods.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testErrorMessageForIncorrectPassword() {
        courier.setPassword("hghf");
        ValidatableResponse response = courierMethods.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Test
    @DisplayName("Авторизация с пустым паролем")
    public void testErrorMessageForNotFoundPassword() {
        courier.setPassword("");
        ValidatableResponse response = courierMethods.loginWithCourier(courier);
        response.assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }
}
