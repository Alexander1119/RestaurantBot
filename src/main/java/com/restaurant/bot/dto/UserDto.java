package com.restaurant.bot.dto;

import java.util.List;

public class UserDto {
  private Integer user_id;
  private List<PersonDto> PersonList;

    public UserDto() {
    }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public List<PersonDto> getPersonList() {
    return PersonList;
  }

  public void setPersonList(List<PersonDto> personList) {
    PersonList = personList;
  }
}
