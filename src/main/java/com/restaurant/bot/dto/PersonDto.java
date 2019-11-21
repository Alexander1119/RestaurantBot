package com.restaurant.bot.dto;

import com.restaurant.bot.domain.Person;

public class PersonDto {

    private Integer person_id;
    private String first_name;
    private String last_name;
    private int cell_phone_num;

    public PersonDto() {
    }

    public PersonDto(Person person) {
        this.first_name = person.getFirstName();
        this.last_name = person.getLastName();
        this.cell_phone_num = person.getCellPhoneNum();
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCell_phone_num() {
        return cell_phone_num;
    }

    public void setCell_phone_num(int cell_phone_num) {
        this.cell_phone_num = cell_phone_num;
    }
}
