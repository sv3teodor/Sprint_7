import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.Order;
import ru.yandex.BaseTest;
import utils.OrderTestUtils;

import static org.apache.http.HttpStatus.SC_CREATED;
import static utils.OrderTestUtils.clearTestOrderDate;

@RunWith(Parameterized.class)
public class CreateOrdersParamTest extends BaseTest {
    private Order order;
    private String[] color;
    private Response response;

    public CreateOrdersParamTest(String[] color) {
        order = new Order().createRandomOrder();
        this.order.setColor(color);
    }

    @Parameterized.Parameters(name = "Проверка создания заказа с разными цветами. Тестовые данные: {0}")
    public static Object[][] getTestParam() {
        return new Object[][]{
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{"", "GRAY"}},
                {new String[]{"BLACK", ""}},
                {new String[]{"", ""}}
        };
    }

    @Before
    public void createOrderForTest() {
        order = new Order();
    }

    @Test
    @DisplayName("Create order test")
    @Description("Test orders with different colors")
    public void createOrderTest() {
        response = OrderTestUtils.createOrder(order);
        response
                .then()
                .statusCode(SC_CREATED)
                .and()
                .assertThat()
                .body("track", Matchers.any(Integer.class));
    }

    @After //Удаляем тестовые данные
    public void cleanTestData() {
        order.setTrack(response.jsonPath().getInt("track"));
        clearTestOrderDate(order);
    }

}

