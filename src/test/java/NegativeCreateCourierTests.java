import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.Courier;
import ru.yandex.BaseTest;
import utils.CouriersTestUtils;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static utils.CouriersTestUtils.clearTestDate;

@RunWith(Parameterized.class)
public class NegativeCreateCourierTests extends BaseTest {
    private final String login;
    private final String password;
    private final String firstName;
    private Courier courier;

    public NegativeCreateCourierTests(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters(name = "Проверка создания логина курьера с неполными данными. Тестовые данные: Login={0} Pass={1} FirstName={2}")
    public static Object[][] getTestParam() {
        return new Object[][]{
                {"", "pass1", "name1",},
                {"login1", "", "name2"},
                {"login1548", "pass1125", ""},
        };
    }

    @Test
    @DisplayName("Courier create test without data")
    @Description("Trying to get a new courier with incomplete data")
    /*Этот тест вернет ошибку на третьем наборе данных при создании курьера без firstName
    В задании четко сказано, что запрос должен вернуть ошибку если одного из полей нет */
    public void createCourierNegativeTest() {
        courier = new Courier(login, password, firstName);
        CouriersTestUtils.createCourier(courier)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", Matchers.is("Недостаточно данных для создания учетной записи"));
    }

    @After //Удаляем тестовые данные
    public void cleanTestData() {
        clearTestDate(courier);
    }

}
