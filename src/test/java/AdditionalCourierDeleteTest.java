import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import pojo.Courier;
import ru.yandex.BaseTest;
import utils.CouriersTestUtils;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static pojo.Courier.makeRandomCourierDate;

public class AdditionalCourierDeleteTest extends BaseTest {
    private Courier courier;

    @Before
    public void createCourierForTest() {
        courier = makeRandomCourierDate();
    }

    @Test
    @DisplayName("Decline delete courier test")
    @Description("Trying delete fake id courier")
    public void declineDelCourierTest() {
        courier.setId("-1");
        CouriersTestUtils.deleteCourier(courier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                //Тест не проходит т.к. сервер возвращает строку с точкой в конце, что не соответствует документации
                .body("message", Matchers.is("Курьера с таким id нет"));
    }

    @Test
    @DisplayName("Decline delete courier test")
    @Description("Trying delete courier without ID")
    public void declineDelCourierWOIdTest() {
        courier.setId("");
        CouriersTestUtils.deleteCourier(courier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                //Тест не проходит т.к. сервер возвращает строку "Not Found", что не соответствует документации
                .body("message", Matchers.is("Курьера с таким id нет"));
    }

    @Test
    @DisplayName("Delete Courier Test")
    @Description("Checking the successful removal of the courier")
    public void deleteCourierTest() {
        CouriersTestUtils.createCourier(courier);
        courier.setId(CouriersTestUtils.loginCourier(courier).jsonPath().getString("id"));
        CouriersTestUtils.deleteCourier(courier)
                .then()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("ok", Matchers.is(true));
    }

}
