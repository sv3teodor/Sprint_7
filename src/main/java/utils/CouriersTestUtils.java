package utils;

import config.ApiRequests;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Courier;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class CouriersTestUtils {
    @Step("Create courier")
    public static Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ApiRequests.COURIER_CREATE);
    }

    @Step("Login courier")
    public static Response loginCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(ApiRequests.COURIER_LOGIN);
    }

    @Step("Courier delete")
    public static Response deleteCourier(Courier courier) {

        return given()
                .delete(ApiRequests.COURIER_DELETE.replace(":id", courier.getId()));
    }


    @Step("Clear test date")
    public static void clearTestDate(Courier courier) {
        //Логинимся под курьером, что бы узнать его id
        //Если курьер найден- удаляем
        Response response = loginCourier(courier);
        if (response.getStatusCode() == SC_OK) {
            deleteCourier(response.as(Courier.class));
        }
    }

}
