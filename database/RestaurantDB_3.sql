-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-12-03 22:46:45.601

-- tables
-- Table: chat
CREATE TABLE public.chat (
    chat_id integer  NOT NULL,
    user_id integer  NOT NULL,
    in_message character varying(500)  NOT NULL,
    out_message character varying(500)  NULL,
    message_date date  NOT NULL,
    tx_user character varying(50)  NOT NULL,
    tx_host character varying(100)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT chat_pk PRIMARY KEY (chat_id)
);

-- Table: cpuser
CREATE TABLE public.cpuser (
    user_id integer  NOT NULL,
    person_id integer  NOT NULL,
    bot_user_id character varying(100)  NOT NULL,
    tx_user character varying(100)  NOT NULL,
    tx_host character varying(100)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (user_id)
);

-- Table: food
CREATE TABLE public.food (
    id_food integer  NOT NULL,
    name_food character varying(100)  NOT NULL,
    cost numeric(15,5)  NOT NULL,
    status integer  NOT NULL,
    CONSTRAINT food_pk PRIMARY KEY (id_food)
);

-- Table: food_foodlist
CREATE TABLE public.food_foodlist (
    food_foodlist_id integer  NOT NULL,
    foodlist_id integer  NOT NULL,
    id_food integer  NOT NULL,
    CONSTRAINT food_foodlist_pk PRIMARY KEY (food_foodlist_id)
);

-- Table: foodlist
CREATE TABLE public.foodlist (
    foodlist_id integer  NOT NULL,
    status integer  NOT NULL,
    CONSTRAINT foodlist_pk PRIMARY KEY (foodlist_id)
);

-- Table: person
CREATE TABLE public.person (
    person_id integer  NOT NULL,
    first_name character varying(50)  NOT NULL,
    last_name character varying(50)  NOT NULL,
    cell_phone_num integer  NOT NULL,
    tx_user character varying(100)  NOT NULL,
    tx_host character varying(100)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT person_pk PRIMARY KEY (person_id)
);

-- Table: restaurant
CREATE TABLE public.restaurant (
    restaurant_id integer  NOT NULL,
    person_id integer  NOT NULL,
    name varchar(30)  NOT NULL,
    street varchar(20)  NOT NULL,
    zone varchar(20)  NOT NULL,
    latitude decimal(15,6)  NOT NULL,
    longitude decimal(15,6)  NOT NULL,
    images varchar(300)  NOT NULL,
    date date  NOT NULL,
    CONSTRAINT restaurant_pk PRIMARY KEY (restaurant_id)
);

-- Table: restaurant_food_type
CREATE TABLE public.restaurant_food_type (
    restaurant_type_food_id integer  NOT NULL,
    restaurant_id integer  NOT NULL,
    id_type_food integer  NOT NULL,
    CONSTRAINT restaurant_food_type_pk PRIMARY KEY (restaurant_type_food_id)
);

-- Table: restaurant_timetable
CREATE TABLE public.restaurant_timetable (
    id_restaurant_timetable integer  NOT NULL,
    restaurant_id integer  NOT NULL,
    timetable_id integer  NOT NULL,
    foodlist_id integer  NOT NULL,
    CONSTRAINT restaurant_timetable_pk PRIMARY KEY (id_restaurant_timetable)
);

-- Table: timetable
CREATE TABLE public.timetable (
    timetable_id integer  NOT NULL,
    day character varying(30)  NOT NULL,
    opening_time time  NOT NULL,
    closing_time time  NOT NULL,
    status integer  NOT NULL,
    CONSTRAINT timetable_pk PRIMARY KEY (timetable_id)
);

-- Table: type_food
CREATE TABLE public.type_food (
    id_type_food integer  NOT NULL,
    name_type character varying(50)  NOT NULL,
    CONSTRAINT type_food_pk PRIMARY KEY (id_type_food)
);

-- foreign keys
-- Reference: chat_cpuser (table: chat)
ALTER TABLE public.chat ADD CONSTRAINT chat_cpuser
    FOREIGN KEY (user_id)
    REFERENCES public.cpuser (user_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cpuser_person (table: cpuser)
ALTER TABLE public.cpuser ADD CONSTRAINT cpuser_person
    FOREIGN KEY (person_id)
    REFERENCES public.person (person_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: food_foodlist_food (table: food_foodlist)
ALTER TABLE public.food_foodlist ADD CONSTRAINT food_foodlist_food
    FOREIGN KEY (id_food)
    REFERENCES public.food (id_food)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: food_foodlist_foodlist (table: food_foodlist)
ALTER TABLE public.food_foodlist ADD CONSTRAINT food_foodlist_foodlist
    FOREIGN KEY (foodlist_id)
    REFERENCES public.foodlist (foodlist_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_food_type_restaurant (table: restaurant_food_type)
ALTER TABLE public.restaurant_food_type ADD CONSTRAINT restaurant_food_type_restaurant
    FOREIGN KEY (restaurant_id)
    REFERENCES public.restaurant (restaurant_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_food_type_type_food (table: restaurant_food_type)
ALTER TABLE public.restaurant_food_type ADD CONSTRAINT restaurant_food_type_type_food
    FOREIGN KEY (id_type_food)
    REFERENCES public.type_food (id_type_food)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_restaurant (table: restaurant)
ALTER TABLE public.restaurant ADD CONSTRAINT restaurant_restaurant
    FOREIGN KEY (person_id)
    REFERENCES public.person (person_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_timetable_food_list (table: restaurant_timetable)
ALTER TABLE public.restaurant_timetable ADD CONSTRAINT restaurant_timetable_food_list
    FOREIGN KEY (foodlist_id)
    REFERENCES public.foodlist (foodlist_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_timetable_restaurant (table: restaurant_timetable)
ALTER TABLE public.restaurant_timetable ADD CONSTRAINT restaurant_timetable_restaurant
    FOREIGN KEY (restaurant_id)
    REFERENCES public.restaurant (restaurant_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: restaurant_timetable_timetable (table: restaurant_timetable)
ALTER TABLE public.restaurant_timetable ADD CONSTRAINT restaurant_timetable_timetable
    FOREIGN KEY (timetable_id)
    REFERENCES public.timetable (timetable_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- sequences
-- Sequence: public.chat_chat_id_seq
CREATE SEQUENCE "public.chat_chat_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.cpuser_user_id_seq
CREATE SEQUENCE "public.cpuser_user_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.food_foodlist_food_foodlist_id_seq
CREATE SEQUENCE "public.food_foodlist_food_foodlist_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.food_id_food_seq
CREATE SEQUENCE "public.food_id_food_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.foodlist_foodlist_id_seq
CREATE SEQUENCE "public.foodlist_foodlist_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.person_person_id_seq
CREATE SEQUENCE "public.person_person_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.place_place_id_seq
CREATE SEQUENCE "public.place_place_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.restaurant_food_type_restaurant_type_food_id_seq
CREATE SEQUENCE "public.restaurant_food_type_restaurant_type_food_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.restaurant_restaurant_id_seq
CREATE SEQUENCE "public.restaurant_restaurant_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.restaurant_timetable_id_restaurant_timetable_seq
CREATE SEQUENCE "public.restaurant_timetable_id_restaurant_timetable_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.street_street_id_seq
CREATE SEQUENCE "public.street_street_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.timetable_timetable_id_seq
CREATE SEQUENCE "public.timetable_timetable_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.type_food_id_type_food_seq
CREATE SEQUENCE "public.type_food_id_type_food_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- Sequence: public.zone_zone_id_seq
CREATE SEQUENCE "public.zone_zone_id_seq"
      NO MINVALUE
      NO MAXVALUE
      NO CYCLE
;

-- End of file.

