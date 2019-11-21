package com.restaurant.bot.dto;

import java.util.List;

public class ClientDto {
  private Integer client_id;
  private List<PersonDto> PersonList;

    public ClientDto() {
    }

  public Integer getClient_id() {
    return client_id;
  }

  public void setClient_id(Integer client_id) {
    this.client_id = client_id;
  }

  public List<PersonDto> getPersonList() {
    return PersonList;
  }

  public void setPersonList(List<PersonDto> personList) {
    PersonList = personList;
  }
}
