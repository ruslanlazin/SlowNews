package ua.pp.lazin.slownews.entity;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlType(propOrder = {"id", "login", "email", "firstName", "lastName"})
@XmlAccessorType(value = XmlAccessType.FIELD)
public class User {
    @XmlElement
    private Long id;

    @XmlElement
    private String login;

    @XmlTransient
    private String password;

    @XmlElement
    private String email;

    @XmlElement
    private String firstName;

    @XmlElement
    private String lastName;

    @XmlElement
    private List<NewsItem> personalNews;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<NewsItem> getPersonalNews() {
        return personalNews;
    }

    public void addNewsItem(NewsItem item) {
        personalNews.add(item);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
