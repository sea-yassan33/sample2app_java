package com.example.sample2app;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name="people")
@NamedQuery(
        name = "findWithName",
        query = "from Person where name like :fname"
)
@NamedQuery(
        name="findByAge",
        query = "from Person where age >= :min and age < :max"
)

public class Person {

  @OneToMany(mappedBy = "Person")
  @Column(nullable = true)
  private List<Message> messages;

  public List<Message> getMessages(){
    return messages;
  }
  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  @NotNull
  private long id;

  @Column(length = 50, nullable = false)
  @NotBlank
  private String name;

  @Column(length = 200, nullable = true)
  @Email
  @NotBlank
  private String mail;

  @Column(nullable = true)
  @Min(value = 0)
  @Max(value =200)
  private Integer age;

  @Column(nullable = true)
  @Phone(onlyNumber = true)
  private String memo;

  public Long getId(){
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }

  public String getMail() {
    return mail;
  }
  public void setMail(String mail){
    this.mail = mail;
  }

  public Integer getAge(){
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }

  public String getMemo(){
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }

}
