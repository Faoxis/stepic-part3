package dbService.dataSets;

import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sergei on 8/18/16.
 */

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false, nullable = false)
    private String login;

    @Column(name = "password", updatable = false, nullable = false)
    private String password;

    @SuppressWarnings("unusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String login, String password) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
    }

    public UsersDataSet(String login, String password) {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(password);
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + login + '\'' +
                '}';
    }
}
