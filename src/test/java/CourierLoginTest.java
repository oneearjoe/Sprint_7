import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.Courier;
import ru.praktikum.CourierMethods;
import static org.hamcrest.Matchers.greaterThan;


@DisplayName("Позитивные тест на логин")
public class CourierLoginTest {

    static CourierMethods courierMethods = new CourierMethods();

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Авторизация с корректным логином и паролем")
    public void loginWithCorrectLoginAndPassword() {
        courierMethods.createCourier(Courier.getRandomCourier());

        ValidatableResponse response = courierMethods.loginWithCourier(Courier.getRandomCourier());

        response.assertThat().body("id", greaterThan(0)).and().statusCode(200);
    }

    @After
    public void deleteCourier() {
        CourierMethods.deleteCourier();
    }
}

