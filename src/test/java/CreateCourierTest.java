import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import ru.praktikum.Courier;
import org.junit.Test;
import io.qameta.allure.junit4.*;
import ru.praktikum.CourierMethods;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;


@DisplayName("Тесты на создание курьера")
public class CreateCourierTest {

    CourierMethods courierMethods = new CourierMethods();

    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание курьера со всеми валидными полями")
    public void checkItIsAbleToCreateNewCourierWithFullValidData(){

        ValidatableResponse response = courierMethods.createCourier(Courier.getRandomCourier());

        response.assertThat().body("ok",is(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Создание курьера без поля firstName")
    public void checkItIsNotAbleToCreateNewCourierWithoutFirstName(){

        courier = Courier.getRandomCourier();

        courier.setFirstName(null);

        ValidatableResponse response = courierMethods.createCourier(courier);

        response.assertThat().body("ok",is(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Создание курьера без поля password")
    public void checkItIsNotAbleToCreateNewCourierWithoutPassword() {
        courier = Courier.getRandomCourier();

        courier.setPassword(null);

        ValidatableResponse response = courierMethods.createCourier(courier);

        response.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера без поля login")
    public void checkItIsNotAbleToCreateNewCourierWithoutLogin() {
        courier = Courier.getRandomCourier();

        courier.setLogin(null);

        ValidatableResponse response = courierMethods.createCourier(courier);

        response.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера с полем login который уже есть в системе")
    public void courierSameLoginFailTest() {

        courierMethods.createCourier(Courier.getRandomCourier());

        ValidatableResponse response = courierMethods.createCourier(Courier.getRandomCourier());

        response.assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой.")).and().statusCode(409);

    }

    @After
    public void deleteCourier() {
        CourierMethods.deleteCourier();
    }
}
