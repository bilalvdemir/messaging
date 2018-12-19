package com.netas.message.model;

import org.springframework.data.annotation.Id;

public class User {

 
    private String username;
    private String password;

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

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + "]";
    }

    // @Id
    // private Long id;
    // @NotEmpty
    // private String name;
    // private Integer age;
    // private double salary;
    //
    // public Long getId() {
    // return id;
    // }
    //
    // public void setId(Long id) {
    // this.id = id;
    // }
    //
    // public String getName() {
    // return name;
    // }
    //
    // public void setName(String name) {
    // this.name = name;
    // }
    //
    // public Integer getAge() {
    // return age;
    // }
    //
    // public void setAge(Integer age) {
    // this.age = age;
    // }
    //
    // public double getSalary() {
    // return salary;
    // }
    //
    // public void setSalary(double salary) {
    // this.salary = salary;
    // }
    //
    // public User(@NotEmpty String name, Integer age, double salary) {
    // super();
    // this.name = name;
    // this.age = age;
    // this.salary = salary;
    // }
    //
    // public User() {
    // super();
    // }
    //
    // @Override
    // public String toString() {
    // return "User [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
    // }

}
