package ru.yandex;

import io.restassured.RestAssured;
import org.junit.Before;

import static config.BaseConfig.BASE_URL;

public abstract class BaseTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }


}
