import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import pojo.Courier;
import ru.yandex.BaseTest;
import utils.CouriersTestUtils;

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static utils.CouriersTestUtils.clearTestDate;

public class CreateCourierTests extends BaseTest {
    private Courier courier;
    private Courier courier2;

    @After //Удаляем тестовые данные
    public void cleanTestData() {
        clearTestDate(courier);
        if (courier2 != null) {
            clearTestDate(courier2);
        }

    }

    @Test
    @DisplayName("Courier create test")
    @Description("Create couriers")
    public void createCourier() {
        courier = new Courier().makeRandomCourierDate();
        CouriersTestUtils.createCourier(courier)
                .then()
                .statusCode(SC_CREATED)
                .and()
                .assertThat()
                .body("ok", Matchers.is(true));
    }

    @Test
    @DisplayName("The dough for creating a double")
    @Description("Trying to create a double")
    public void createDoubleCouriers() {
        courier = new Courier().makeRandomCourierDate();
        CouriersTestUtils.createCourier(courier)
                .then()
                .statusCode(SC_CREATED);
        CouriersTestUtils.createCourier(courier)
                .then()
                .statusCode(SC_CONFLICT).body("message", Matchers.is("Этот логин уже используется"));
        //Тут баг- текст возвращаемый системой системы не совпадает с описанием.

    }


    @Test
    @DisplayName("Create couriers with equal logins")
    @Description("Try create two couriers with equal logins")
    public void createCouriersWithEqualLogin() {
        courier = new Courier().makeRandomCourierDate();
        courier2 = new Courier().makeRandomCourierDate();
        courier2.setLogin(courier.getLogin());
        CouriersTestUtils.createCourier(courier)
                .then()
                .statusCode(SC_CREATED);
        String s = "Trying create second courier";
        CouriersTestUtils.createCourier(courier2)
                .then()
                .statusCode(SC_CONFLICT);

    }

}


