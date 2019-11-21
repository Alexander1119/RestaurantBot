-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-11-16 00:06:58.242

-- tables
-- Table: Person
CREATE TABLE Person (
    person_id serial  NOT NULL,
    first_name varchar(50)  NOT NULL,
    last_name varchar(50)  NOT NULL,
    cell_phone_num int  NOT NULL,
    tx_user varchar(100)  NOT NULL,
    tx_host varchar(100)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT Person_pk PRIMARY KEY (person_id)
);

-- Table: Restaurant_food_type
CREATE TABLE Restaurant_food_type (
    restaurant_type_food_id serial  NOT NULL,
    restaurant_id int  NOT NULL,
    id_type_food int  NOT NULL,
    CONSTRAINT Restaurant_food_type_pk PRIMARY KEY (restaurant_type_food_id)
);

-- Table: client
CREATE TABLE client (
    client_id serial  NOT NULL,
    person_id int  NOT NULL,
    bot_user_id int  NOT NULL,
    convesacion_id int  NOT NULL,
    last_message_send varchar(500)  NOT NULL,
    last_message_receiv varchar(500)  NOT NULL,
    tx_user varchar(100)  NOT NULL,
    tx_host varchar(100)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT client_pk PRIMARY KEY (client_id)
);

-- Table: food
CREATE TABLE food (
    id_food serial  NOT NULL,
    name_food varchar(100)  NOT NULL,
    cost numeric(15,5)  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT food_pk PRIMARY KEY (id_food)
);

-- Table: foodList
CREATE TABLE foodList (
    foodList_id serial  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT foodList_pk PRIMARY KEY (foodList_id)
);

-- Table: food_foodList
CREATE TABLE food_foodList (
    food_foodList_id serial  NOT NULL,
    foodList_id int  NOT NULL,
    id_food int  NOT NULL,
    CONSTRAINT food_foodList_pk PRIMARY KEY (food_foodList_id)
);

-- Table: place
CREATE TABLE place (
    place_id serial  NOT NULL,
    name_place varchar(100)  NOT NULL,
    zone_id int  NOT NULL,
    CONSTRAINT place_pk PRIMARY KEY (place_id)
);

-- Table: restaurant
CREATE TABLE restaurant (
    restaurant_id serial  NOT NULL,
    name int  NOT NULL,
    person_id int  NOT NULL,
    place_id int  NOT NULL,
    CONSTRAINT restaurant_pk PRIMARY KEY (restaurant_id)
);

-- Table: restaurant_timetable
CREATE TABLE restaurant_timetable (
    id_restaurant_timetable serial  NOT NULL,
    restaurant_id int  NOT NULL,
    timetable_id int  NOT NULL,
    foodList_id int  NOT NULL,
    CONSTRAINT restaurant_timetable_pk PRIMARY KEY (id_restaurant_timetable)
);

-- Table: street
CREATE TABLE street (
    street_id serial  NOT NULL,
    name_street varchar(100)  NOT NULL,
    CONSTRAINT street_pk PRIMARY KEY (street_id)
);

-- Table: timetable
CREATE TABLE timetable (
    timetable_id serial  NOT NULL,
    day varchar(30)  NOT NULL,
    opening_time time  NOT NULL,
    closing_time time  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT timetable_pk PRIMARY KEY (timetable_id)
);

-- Table: type_food
CREATE TABLE type_food (
    id_type_food serial  NOT NULL,
    name_type varchar(50)  NOT NULL,
    CONSTRAINT type_food_pk PRIMARY KEY (id_type_food)
);

-- Table: zone
CREATE TABLE zone (
    zone_id serial  NOT NULL,
    name_zone varchar(100)  NOT NULL,
    street_id int  NOT NULL,
    CONSTRAINT zone_pk PRIMARY KEY (zone_id)
);

-- foreign keys
-- Reference: Restaurant_food_type_restaurant (table: Restaurant_food_type)
ALTER TABLE Restaurant_food_type ADD CONSTRAINT Restaurant_food_type_restaurant
    FOREIGN KEY (restaurant_id)
    REFERENCES restaurant (restaurant_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Restaurant_food_type_type_food (table: Restaurant_food_type)
ALTER TABLE Restaurant_food_type ADD CONSTRAINT Restaurant_food_type_type_food
    FOREIGN KEY (id_type_food)
    REFERENCES type_food (id_type_food)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: city_zone (table: place)
ALTER TABLE place ADD CONSTRAINT city_zone
    FOREIGN KEY (zone_id)
    REFERENCES zone (zone_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: client_Person (table: client)
ALTER TABLE client ADD CONSTRAINT client_Person
    FOREIGN KEY (person_id)
    REFERENCES Person (person_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: food_foodList_food (table: food_foodList)
ALTER TABLE food_foodList ADD CONSTRAINT food_foodList_food
    FOREIGN KEY (id_food)
    REFERENCES food (id_food)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: food_foodList_foodList (table: food_foodList)
ALTER TABLE food_foodList ADD CONSTRAINT food_foodList_foodList
    FOREIGN KEY (foodList_id)
    REFERENCES foodList (foodList_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_Restaurant (table: restaurant)
ALTER TABLE restaurant ADD CONSTRAINT restaurant_Restaurant
    FOREIGN KEY (person_id)
    REFERENCES Person (person_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_place (table: restaurant)
ALTER TABLE restaurant ADD CONSTRAINT restaurant_place
    FOREIGN KEY (place_id)
    REFERENCES place (place_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_timetable_food_list (table: restaurant_timetable)
ALTER TABLE restaurant_timetable ADD CONSTRAINT restaurant_timetable_food_list
    FOREIGN KEY (foodList_id)
    REFERENCES foodList (foodList_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_timetable_restaurant (table: restaurant_timetable)
ALTER TABLE restaurant_timetable ADD CONSTRAINT restaurant_timetable_restaurant
    FOREIGN KEY (restaurant_id)
    REFERENCES restaurant (restaurant_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_timetable_timetable (table: restaurant_timetable)
ALTER TABLE restaurant_timetable ADD CONSTRAINT restaurant_timetable_timetable
    FOREIGN KEY (timetable_id)
    REFERENCES timetable (timetable_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: zone_street (table: zone)
ALTER TABLE zone ADD CONSTRAINT zone_street
    FOREIGN KEY (street_id)
    REFERENCES street (street_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

