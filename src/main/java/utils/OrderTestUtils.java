package utils;

import config.ApiRequests;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import pojo.Courier;
import pojo.Order;

import static io.restassured.RestAssured.given;

public class OrderTestUtils {
    @Step("Create new order")
    public static Response createOrder(Order order) {
        return given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ApiRequests.ORDER_CREATE);
    }

    @Step("Order delete")
    public static Response deleteOrder(Order order) {
        return given()
                .queryParam("track", String.valueOf(order.getTrack()))
                .put(ApiRequests.ORDER_CANCEL);
    }

    @Step("Clear test date")
    public static void clearTestOrderDate(Order order) {
        if (order.getTrack() != 0) {
            deleteOrder(order);
        }

    }

    @Step("Clear test order date by track")
    public static void clearTestOrderDateByTrack(Integer track) {
        Order order = new Order();
        order.setTrack(track);
        clearTestOrderDate(order);
    }


    @Step("Order accept couriers")
    public static Response orderAccept(Order order, Courier courier) {

        String reqTrackString = ApiRequests.ORDER_ACCEPT;

        if (order.getTrack() != null) {
            reqTrackString = ApiRequests.ORDER_ACCEPT.replace(":id", order.getTrack().toString());
        } else {
            reqTrackString = ApiRequests.ORDER_ACCEPT.replace(":id", "");
        };

        RequestSpecification req = given();
        req.contentType(ContentType.JSON);
        if ((courier.getId() != null) && StringUtils.isNumeric(courier.getId())) {
            req.queryParam("courierId", Integer.valueOf(courier.getId()));
        }
        System.out.println(reqTrackString);
        return req.put(reqTrackString);
    }

    @Step("Get orders list by courier ID")
    public static Response getOrderList(Courier courier) {
        return given()
                .queryParam("courierId", courier.getId())
                .get(ApiRequests.ORDER_GET_LIST);
    }

    @Step("Get orders list wo param")
    public static Response getOrderList() {
        return given().get(ApiRequests.ORDER_GET_LIST);
    }

}
