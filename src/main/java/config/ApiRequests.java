package config;

public class ApiRequests {
    public static final String COURIER_LOGIN = "/api/v1/courier/login";
    public static final String COURIER_CREATE = "/api/v1/courier";
    public static final String COURIER_DELETE = "/api/v1/courier/:id";

    public static final String ORDER_CREATE = "/api/v1/orders";
    public static final String ORDER_CANCEL = "/api/v1/orders/cancel";
    public static final String ORDER_ACCEPT = "/api/v1/orders/accept/:id";
    public static final String ORDER_GET_LIST = "/api/v1/orders";


}
