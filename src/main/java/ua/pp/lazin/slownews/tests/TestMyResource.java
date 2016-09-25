package ua.pp.lazin.slownews.tests;


import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import ua.pp.lazin.slownews.entity.User;
import ua.pp.lazin.slownews.entity.Users;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class TestMyResource {

    public static void main(String[] args) {
        {
            final String url = "http://localhost:8080/api/users";
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);

            Invocation.Builder invocationBuilder = target.request(javax.ws.rs.core.MediaType.APPLICATION_XML);
            Response response = invocationBuilder.get();

            Users users = response.readEntity(Users.class);

            StringBuilder builder = new StringBuilder
                    ("======================================= Users =======================================\n");
            for (User user : users.getUsers()) {
                builder.append("Id: ").append(user.getId()).append(", ")
                        .append("Login: ").append(user.getLogin()).append(", ")
                        .append("Email: ").append(user.getEmail()).append(", ")
                        .append("Firstname: ").append(user.getFirstName()).append(", ")
                        .append("LastName: ").append(user.getLastName())
                        .append("\n");
            }
            builder.append("=====================================================================================");
            System.out.println(builder.toString());

            String xmlString = invocationBuilder.get().readEntity(String.class);
            System.out.println("\n The xml code sent from server is below: \n");
            System.out.println(xmlString);
        }

    }
}