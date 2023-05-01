import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.Courier;
import pojo.Order;
import ru.yandex.BaseTest;
import utils.CouriersTestUtils;
import utils.OrderTestUtils;

import static org.apache.http.HttpStatus.*;
import static pojo.Courier.makeRandomCourierDate;
import static utils.CouriersTestUtils.clearTestDate;
import static utils.OrderTestUtils.clearTestOrderDate;

public class AdditionalGetOrderTest extends BaseTest {
    private Order order;
    private Courier courier;

    @Before
    public void createOrderAndCourierForTest() {
        courier = makeRandomCourierDate();
        CouriersTestUtils.createCourier(courier);//Создаем курьера
        courier.setId(CouriersTestUtils.loginCourier(courier).jsonPath().getString("id")); //Логинимся под ним
        order = new Order().createRandomOrder();
        order.setTrack(OrderTestUtils.createOrder(order).body().jsonPath().getInt("track"));
    }

    @After //Удаляем тестовые данные
    public void cleanTestData() {
           clearTestOrderDate(order);
        if (courier != null) {
            clearTestDate(courier);
        }
    }

    @Test
    @DisplayName("Get orders list")
    @Description("Checking the successful binding of the order to the courier")
    public void checkOrdersList() throws InterruptedException {
        //Привязываем заказ к курьеру
        OrderTestUtils.orderAccept(order, courier)
                .then()
                .statusCode(SC_OK); // Этот тест иногда не проходит.
    }

    @Test
    @DisplayName("Get orders list without couriers ID")
    @Description("Checking if the courier ID has not been transmitted")
    public void checkOrdersListWOCouriersID() {
        courier.setId(null);
        CouriersTestUtils.createCourier(courier);//Создаем курьера
        OrderTestUtils.orderAccept(order, courier)
                .then()
                .statusCode(SC_BAD_REQUEST);
    }


    @Test
    @DisplayName("Get orders without incorrect track")
    @Description("Checking if the orders with incorrect track")
    public void checkOrdersListWithIncorrectTrack() {
        order.setTrack(-1);
        OrderTestUtils.orderAccept(order, courier)
                .then()
                .statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Check orders list with incorrect ID")
    @Description("Checking if the orders with incorrect couriers ID")
    public void checkOrdersListWithIncorrectID() {
        courier.setId("-1");
        OrderTestUtils.orderAccept(order, courier)
                .then()
                .statusCode(SC_BAD_REQUEST);
    }
}



