package com.example.sample2app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name="people")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  @NotNull
  private long id;

  @Column(length = 50, nullable = false)
  @NotBlank(message = "名無しの権兵衛")
  private String name;

  @Column(length = 200, nullable = true)
  @Email
  @NotBlank(message = "メールがない")
  private String mail;

  @Column(nullable = true)
  @Min(value = 0, message = "マイナスってありえない")
  @Max(value =200, message = "見直して！")
  private Integer age;

  @Column(nullable = true)
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
