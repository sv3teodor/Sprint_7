package pojo;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private String login;
    private String password;
    private String firstName;
    private String id;

    public Courier() {

    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Courier makeRandomCourierDate() {
        this.login = RandomStringUtils.randomAlphabetic(3, 10);
        this.password = RandomStringUtils.randomAlphabetic(3, 8);
        this.firstName = RandomStringUtils.randomAlphabetic(3, 10);
        return this;
    }
}
