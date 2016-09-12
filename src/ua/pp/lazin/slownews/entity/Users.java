package ua.pp.lazin.slownews.entity;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlType
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Users {

    @XmlElement(name = "user")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
