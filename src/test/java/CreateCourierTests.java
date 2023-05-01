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
import static pojo.Courier.makeRandomCourierDate;
import static utils.CouriersTestUtils.clearTestDate;

public class CreateCourierTests extends BaseTest {
    private Courier courierFirst;
    private Courier courierSecond;

    @After //Удаляем тестовые данные
    public void cleanTestData() {
        clearTestDate(courierFirst);
        if (courierSecond != null) {
            clearTestDate(courierSecond);
        }

    }

    @Test
    @DisplayName("Courier create test")
    @Description("Create couriers")
    public void createCourier() {
        courierFirst = makeRandomCourierDate();
        CouriersTestUtils.createCourier(courierFirst)
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
        courierFirst = makeRandomCourierDate();
        CouriersTestUtils.createCourier(courierFirst)
                .then()
                .statusCode(SC_CREATED);
        CouriersTestUtils.createCourier(courierFirst)
                .then()
                .statusCode(SC_CONFLICT).body("message", Matchers.is("Этот логин уже используется"));
        //Тут баг- текст возвращаемый системой системы не совпадает с описанием.

    }


    @Test
    @DisplayName("Create couriers with equal logins")
    @Description("Try create two couriers with equal logins")
    public void createCouriersWithEqualLogin() {
        courierFirst = makeRandomCourierDate();
        courierSecond = makeRandomCourierDate();
        courierSecond.setLogin(courierFirst.getLogin());
        CouriersTestUtils.createCourier(courierFirst)
                .then()
                .statusCode(SC_CREATED);
        CouriersTestUtils.createCourier(courierSecond)
                .then()
                .statusCode(SC_CONFLICT);
    }

}


