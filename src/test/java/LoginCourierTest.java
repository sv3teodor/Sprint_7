import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.Courier;
import ru.yandex.BaseTest;
import utils.CouriersTestUtils;

import static org.apache.http.HttpStatus.*;
import static pojo.Courier.makeRandomCourierDate;
import static utils.CouriersTestUtils.clearTestDate;

public class LoginCourierTest extends BaseTest {
    private Courier courier;

    @Before
    public void createCourierForTest() {
        courier = makeRandomCourierDate();
        CouriersTestUtils.createCourier(courier);
    }

    @After //Удаляем тестовые данные
    public void cleanTestData() {
        clearTestDate(courier);
    }

    @Test
    @DisplayName("Courier login")
    @Description("Positive test of the courier's login")
    public void loginCourier() {
        CouriersTestUtils.loginCourier(courier)
                .then()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("id", Matchers.any(Integer.class));
    }

    @Test
    @DisplayName("Courier login ")
    @Description("Test login without login")
    public void loginCourierWOLogin() {
        courier.setLogin("");
        CouriersTestUtils.loginCourier(courier)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", Matchers.is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier login ")
    @Description("Test login witout password")
    public void loginCourierWOPass() {
        courier.setPassword("");
        CouriersTestUtils.loginCourier(courier)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", Matchers.is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier login ")
    @Description("Test login with incorrect password")
    public void loginCourierIncorrectPass() {
        courier.setPassword(courier.getPassword() + "1");
        CouriersTestUtils.loginCourier(courier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                .body("message", Matchers.is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Courier login ")
    @Description("Test login with incorrect login")
    public void loginCourierWOIncorrectLogin() {
        courier.setLogin(courier.getLogin() + "1");
        CouriersTestUtils.loginCourier(courier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                .body("message", Matchers.is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Courier login with Incorrect All User Data ")
    @Description("Test login with unregistered user")
    public void loginCourierWOIncorrectAllUserData() {
        courier.setLogin(courier.getLogin() + courier.getLogin());
        courier.setPassword(courier.getPassword() + courier.getPassword());
        CouriersTestUtils.loginCourier(courier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                .body("message", Matchers.is("Учетная запись не найдена"));
    }

}
