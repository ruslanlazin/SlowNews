package ua.pp.lazin.slownews.entity;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlType
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Users implements Serializable{

    @XmlElement(name = "user")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
