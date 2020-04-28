package pl.pk.project.pz.sound_intensity.dao.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    @Length(max = 128)
    private String username;

    private String password;

    public User(@Length(max = 128) String username, String password) {
        this.username = username;
        this.password = password;
    }
     public User(){
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
