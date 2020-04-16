package com.project.finance.model;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.List;

@Entity
@Table(name = "Users")
@SequenceGenerator(name="seqUser", initialValue=1, allocationSize=100)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seqUser")
   // @OneToMany(mappedBy = "user")
  //  @JoinColumn
    @Column
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public User (){}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public String toString(){return "User ID: "+id+"   Name: "+firstName+' '+lastName+'\n';}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
