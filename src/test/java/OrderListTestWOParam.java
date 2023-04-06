import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pojo.OrderList;
import ru.yandex.BaseTest;
import utils.OrderTestUtils;

public class OrderListTestWOParam extends BaseTest {
    @Test
    @DisplayName("Get orders list")
    @Description("Check orders list in response")
    public void checkOrdersList() {
        //Запрашиваем заказы
        OrderList orderList = OrderTestUtils.getOrderList().as(OrderList.class);
        //Проверяем, что массов заказов состоит из более чем нуля элементов
        Assert.assertTrue("В ответе нет заказов", orderList.getOrders().length > 0);
    }
}

