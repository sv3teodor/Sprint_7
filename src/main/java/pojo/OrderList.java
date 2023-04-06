package pojo;

public class OrderList {
    private Order[] orders;
    private PageInfo pageInfo;
    private StationsList[] availableStations;

    public OrderList() {
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public StationsList[] getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(StationsList[] availableStations) {
        this.availableStations = availableStations;
    }
}
