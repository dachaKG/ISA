INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('guest1','pass1','mika','mikic','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('guest2','pass2','mika','mikic','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('guest3','pass3','mika','mikic','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('5','5','mika','mikic','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('pera@gmail.com','pera','Petar','Peric','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('pera2@gmail.com','pera','Petar','Peric','1');

INSERT INTO BOSS (MAIL, PASSWORD, FIRSTNAME, LASTNAME,REGISTRATED) VALUES('1','1','mika','mikic','1');

INSERT INTO SYSTEM_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('2@2','2','mika','mikic','1');
INSERT INTO SYSTEM_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('2','2','mika','mikic','1');

INSERT INTO RESTAURANT_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('3','3','mika1','mikic','1');
INSERT INTO RESTAURANT_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('4@4','4','mika2','mikic','1');
INSERT INTO RESTAURANT_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('r_manager3','manager3','mika3','mikic','1');


INSERT INTO RESTAURANT (NAME,COUNTRY,CITY,STREET,NUMBER,SUMM_RATE) VALUES('restaurant1','Serbia','Novi Sad','Bulervar oslobodjenja',25,3.6);
INSERT INTO RESTAURANT (NAME,COUNTRY,CITY,STREET,NUMBER,SUMM_RATE) VALUES('restaurant2','Serbia','Novi Sad','Fruskogorska',17,4);

INSERT INTO RESTAURANT_MANAGERS_OF_RESTAURANT (RESTAURANT_ID,RESTAURANT_MANAGER_ID) VALUES (1,1)
INSERT INTO RESTAURANT_MANAGERS_OF_RESTAURANT (RESTAURANT_ID,RESTAURANT_MANAGER_ID) VALUES (2,2)


INSERT INTO WAITER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,DEFAULT_SHIFT,RATE) VALUES ('9@9','9','milanko','milanovic','1','M','no45','Second',4)
INSERT INTO WAITER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,DEFAULT_SHIFT,RATE) VALUES ('waiter1@gmail.com','1','waiter1','zzz','1','M','no45','First',0)
INSERT INTO WAITER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,DEFAULT_SHIFT,RATE) VALUES ('waiter2@gmail.com','1','waiter2','zzz','1','M','no45','Second',3)
INSERT INTO WAITER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,DEFAULT_SHIFT,RATE) VALUES ('waiter3@gmail.com','1','waiter3','zzz','1','M','no45','Second',0)

INSERT INTO COOK (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,TYPE_OF_COOKER, DEFAULT_SHIFT) VALUES ('8','8','milance','milanovic','1','M','no46','baked','First')
INSERT INTO COOK (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,TYPE_OF_COOKER, DEFAULT_SHIFT) VALUES ('8@8','8','milance','milanovic','1','M','no46','salad','First')

INSERT INTO BARTENDER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,DEFAULT_SHIFT) VALUES ('a@gmail.com','a','milan','milanovic','1','M','no45','First')
INSERT INTO BARTENDER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE,DEFAULT_SHIFT) VALUES ('saAA@ss','passw','milan','milanovic','1','M','no45','Second')


INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('6','6','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder1@gmail.com','1','pera','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder2@gmail.com','1','zika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder3@gmail.com','1','z','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder4@gmail.com','1','q','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder5@gmail.com','1','w','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder6@gmail.com','1','e','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder7@gmail.com','1','t','mikic',1);


INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Kola1',100,'sok',50)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Kola2',105,'sok',40)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Pivo',150,'pivo',60)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Kola3',110,'sok',60)


INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH,RATE) VALUES('Jelo1',110,'jelo',60,'salad',5)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH,RATE) VALUES('Jelo2',110,'jelo',62,'baked',4.5)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH,RATE) VALUES('Jelo3',110,'jelo',60,'baked',3.3)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH,RATE) VALUES('Jelo4',110,'jelo',64,'cooked',0)


INSERT INTO DRINK_ORDER (DRINK_ID,COUNT) VALUES (1,30)
INSERT INTO DRINK_ORDER (DRINK_ID,COUNT) VALUES (3,50)

INSERT INTO DISH_ORDER (DISH_ID,COUNT) VALUES (1,30)
INSERT INTO DISH_ORDER (DISH_ID,COUNT) VALUES (3,50)

INSERT INTO ORDER_BIDDER() VALUES()

INSERT INTO ORDER_BIDDER_DRINKS (ORDER_BIDDER_ID,DRINK_ORDER_ID) VALUES (1,1)
INSERT INTO ORDER_BIDDER_DRINKS (ORDER_BIDDER_ID,DRINK_ORDER_ID) VALUES (1,2)

INSERT INTO ORDER_BIDDER_FOOD (ORDER_BIDDER_ID,DISH_ORDER_ID) VALUES (1,1)
INSERT INTO ORDER_BIDDER_FOOD (ORDER_BIDDER_ID,DISH_ORDER_ID) VALUES (1,2)

INSERT INTO RESTAURANT_DRINK (RESTAURANT_ID,DRINK_ID) VALUES (1,1)
INSERT INTO RESTAURANT_DRINK (RESTAURANT_ID,DRINK_ID) VALUES (2,2)
INSERT INTO RESTAURANT_DRINK (RESTAURANT_ID,DRINK_ID) VALUES (1,3)

INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (1,1)
INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (1,2)
INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (1,3)
INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (2,4)

INSERT INTO ORDERR (TOTAL) VALUES(215)
INSERT INTO ORDERR (FOOD_STATUS, DRINK_STATUS, TOTAL) VALUES('finished','finished',215)
INSERT INTO ORDERR (FOOD_STATUS, DRINK_STATUS, TOTAL) VALUES('finished','finished',155)
INSERT INTO ORDERR (TOTAL) VALUES(144)
INSERT INTO ORDERR (FOOD_STATUS, DRINK_STATUS, TOTAL) VALUES('finished','finished',370)
INSERT INTO ORDERR (TOTAL) VALUES(260)
INSERT INTO ORDERR (FOOD_STATUS, DRINK_STATUS, TOTAL)  VALUES('inPrepared','inPrepared',260)
INSERT INTO ORDERR (TOTAL) VALUES(260)
INSERT INTO ORDERR (FOOD_STATUS, DRINK_STATUS, TOTAL)  VALUES('inPrepared','inPrepared',500)
INSERT INTO ORDERR (TOTAL)  VALUES(450)
INSERT INTO ORDERR (FOOD_STATUS, DRINK_STATUS, TOTAL)  VALUES('inPrepared','inPrepared',400)
INSERT INTO ORDERR (FOOD_STATUS, DRINK_STATUS, TOTAL)  VALUES('inPrepared','inPrepared',600)
INSERT INTO ORDERR (TOTAL)  VALUES(200)




INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (1,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (2,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (3,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (1,1)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (5,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (6,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (6,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (7,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (7,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (7,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (9,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (9,1)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (10,1)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (10,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (10,1)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (10,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (11,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (11,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (12,1)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (12,1)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (13,3)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (13,3)



INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (1,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (1,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (1,3)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (1,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (2,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (3,3)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (4,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (4,3)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (5,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (5,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (6,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (6,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (8,3)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (7,3)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (9,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (9,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (10,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (10,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (10,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (11,3)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (11,3)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (12,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (12,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (12,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (13,1)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (13,1)


INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (2,1)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,2)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,4)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,5)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,6)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (2,7)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,8)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,9)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,10)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,11)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,12)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (1,13)





INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,2)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,4)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,5)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (3,6)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (3,8)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (4,5)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (3,7)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,8)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,9)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,10)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,11)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,12)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (3,13)



INSERT INTO BARTENDER_ORDERS (BARTENDER_ID, ORDER_ID) VALUES (1,3)

INSERT INTO COOK_ORDER (COOK_ID, ORDER_ID,DISH_STATUS) VALUES (1,2,'nothing')
INSERT INTO COOK_ORDER (COOK_ID, ORDER_ID,DISH_STATUS) VALUES (1,3,'nothing')
INSERT INTO COOK_ORDER (COOK_ID, ORDER_ID,DISH_STATUS) VALUES (2,3,'nothing')


INSERT INTO RESTAURANT_BIDDERS (BIDDER_ID, RESTAURANT_ID) VALUES (1,1)
INSERT INTO RESTAURANT_BIDDERS (BIDDER_ID, RESTAURANT_ID) VALUES (2,1)
INSERT INTO RESTAURANT_BIDDERS (BIDDER_ID, RESTAURANT_ID) VALUES (3,1)
INSERT INTO RESTAURANT_BIDDERS (BIDDER_ID, RESTAURANT_ID) VALUES (1,2)
INSERT INTO RESTAURANT_BIDDERS (BIDDER_ID, RESTAURANT_ID) VALUES (2,2)



INSERT INTO RESTAURANT_BARTENDERS (BARTENDER_ID, RESTAURANT_ID) VALUES (1,1)
INSERT INTO RESTAURANT_BARTENDERS (BARTENDER_ID, RESTAURANT_ID) VALUES (2,1)


INSERT INTO RESTAURANT_COOKS (COOK_ID, RESTAURANT_ID) VALUES (1,1)
INSERT INTO RESTAURANT_COOKS (COOK_ID, RESTAURANT_ID) VALUES (2,1)

INSERT INTO RESTAURANT_WAITERS (WAITER_ID, RESTAURANT_ID) VALUES (1,1)
INSERT INTO RESTAURANT_WAITERS (WAITER_ID, RESTAURANT_ID) VALUES (2,1)
INSERT INTO RESTAURANT_WAITERS (WAITER_ID, RESTAURANT_ID) VALUES (3,1)
INSERT INTO RESTAURANT_WAITERS (WAITER_ID, RESTAURANT_ID) VALUES (4,1)


INSERT INTO RESTAURANT_ORDERR (ORDER_ACTIVE, START_DATE,END_DATE,DISH_DISH_ID,COUNT) VALUES ('open','2016-11-11','2017-11-11',1,30)
INSERT INTO RESTAURANT_ORDERR (ORDER_ACTIVE, START_DATE,END_DATE,DISH_DISH_ID,COUNT) VALUES ('open','2016-11-11','2017-11-11',2,50)


INSERT INTO OFFER (PRICE, BIDDER_BIDDER_ID,ACCEPTED,POSIBLE_DELIVERY,GARANTY) VALUES (60,1,'in progress','2016-6-8',10)
INSERT INTO OFFER (PRICE, BIDDER_BIDDER_ID,ACCEPTED,POSIBLE_DELIVERY,GARANTY) VALUES (30,2,'in progress','2016-7-6',15)
INSERT INTO OFFER (PRICE, BIDDER_BIDDER_ID,ACCEPTED,POSIBLE_DELIVERY,GARANTY) VALUES (30,1,'in progress','2016-8-7',13)


INSERT INTO RESTAURANT_OFFER (RESTAURANT_ORDER_ID, OFFER_ID) VALUES (1,1)
INSERT INTO RESTAURANT_OFFER (RESTAURANT_ORDER_ID, OFFER_ID) VALUES (1,2)


INSERT INTO RESTAURANT_RESTAURANT_ORDER (RESTAURANT_ID,RESTAURANT_ORDER_ID) VALUES (1,1)
INSERT INTO RESTAURANT_RESTAURANT_ORDER (RESTAURANT_ID,RESTAURANT_ORDER_ID) VALUES (1,2)

INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-2-8',14,13,2,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-2-15',14,18,2,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-2-5',10,13,1.5,2);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-10',15,11,3,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-24',19,4,1,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-25',10,4,5,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-25',18,4,3,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-25',12,4,5,1);
--
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-26',13,4,2,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-26',14,4,2,1);
INSERT INTO RESERVATION (DATE,HOURS,MINUTES,DURATION,RESTAURANT_RESTAURANT_ID) VALUES ('2017-02-26',15,4,3,1);



INSERT INTO BILL(DATE, TOTAL) VALUES ('2017-2-8', 260)
INSERT INTO BILL(DATE, TOTAL) VALUES ('2017-02-10', 260)
INSERT INTO BILL(DATE, TOTAL) VALUES ('2017-2-15', 260)
INSERT INTO BILL(DATE, TOTAL) VALUES ('2017-2-5', 260)

INSERT INTO BILL_RESERVATION(RESERVATION_ID,BILL_ID) VALUES (1,1)
INSERT INTO BILL_RESERVATION(RESERVATION_ID,BILL_ID) VALUES (2,4)
INSERT INTO BILL_RESERVATION(RESERVATION_ID,BILL_ID) VALUES (3,2)
INSERT INTO BILL_RESERVATION(RESERVATION_ID,BILL_ID) VALUES (4,3)

INSERT INTO WAITER_BILLS(WAITER_ID, BILL_ID) VALUES (1,1)
INSERT INTO WAITER_BILLS(WAITER_ID, BILL_ID) VALUES (1,2)
INSERT INTO WAITER_BILLS(WAITER_ID, BILL_ID) VALUES (1,3)
INSERT INTO WAITER_BILLS(WAITER_ID, BILL_ID) VALUES (1,4)

INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (1,1);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (1,2);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (1,4);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (4,4);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (4,3);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (5,3);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (5,4);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (7,4);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (8,4);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (8,2);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (9,1);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (9,2);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (9,3);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (10,2);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (10,1);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (11,4);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (12,3);
INSERT INTO RESERVATION_GUEST (RESERVATION_ID,GUEST_ID) VALUES (12,4);


INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (1,2);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (4,5);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (5,6);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (7,8);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (6,7);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (8,9);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (9,10);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (10,11);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (11,12);
INSERT INTO RESERVATION_ORDER (RESERVATION_ID,ORDER_ID) VALUES (11,13);



--INSERT INTO RESERVATION_RESTAURANT (RESERVATION_ID, RESTAURANT_ID) VALUES (4,1)
--INSERT INTO RESERVATION_RESTAURANT (RESERVATION_ID, RESTAURANT_ID) VALUES (1,1)


--INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,2)
--INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,1)

--INSERT INTO WAITER_RESTAURANT(WAITER_ID, RESTAURANT_ID) VALUES (1,1)


-- pravljenje konfiguracije stolova za restoran 1
insert into segment(name) values ('Balkon')
insert into segment(name) values ('Nepusacki')
insert into restaurant_segment(restaurant_id, segment_id) values (1,1)
insert into restaurant_segment(restaurant_id, segment_id) values (1,2)


insert into table(name, segment_name, status, x_pos, y_pos) values ('1', 'Nepusacki', 'Exists', 0, 0)
insert into table(name, segment_name, status, x_pos, y_pos) values ('2', 'Nepusacki', 'Exists', 0, 1)
insert into table(name, segment_name, status, x_pos, y_pos) values ('3', 'Nepusacki', 'Exists', 0, 2)
insert into table(name, segment_name, status, x_pos, y_pos) values ('4', 'Nepusacki', 'Exists', 0, 3)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 0, 4)
insert into table(name, segment_name, status, x_pos, y_pos) values ('5', 'Nepusacki', 'Exists', 0, 5)
insert into table(name, segment_name, status, x_pos, y_pos) values ('6', 'Nepusacki', 'Exists', 0, 6)
insert into table(name, segment_name, status, x_pos, y_pos) values ('7', 'Nepusacki', 'Exists', 0, 7)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 1, 0)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 1, 1)

insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 1, 2)
insert into table(name, segment_name, status, x_pos, y_pos) values ('8', 'Nepusacki', 'Exists', 1, 3)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 1, 4)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 1, 5)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 1, 6)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 1, 7)
insert into table(name, segment_name, status, x_pos, y_pos) values ('9', 'Nepusacki', 'Exists', 2, 0)
insert into table(name, segment_name, status, x_pos, y_pos) values ('10', 'Nepusacki', 'Exists', 2, 1)
insert into table(name, segment_name, status, x_pos, y_pos) values ('11', 'Nepusacki', 'Exists', 2, 2)
insert into table(name, segment_name, status, x_pos, y_pos) values ('12', 'Nepusacki', 'Exists', 2, 3)

insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 2, 4)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 2, 5)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 2, 6)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 2, 7)
insert into table(name, segment_name, status, x_pos, y_pos) values ('13', 'Nepusacki', 'Exists', 3, 0)
insert into table(name, segment_name, status, x_pos, y_pos) values ('13a', 'Nepusacki', 'Exists', 3, 1)
insert into table(name, segment_name, status, x_pos, y_pos) values ('14', 'Nepusacki', 'Exists', 3, 2)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 3, 3)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 3, 4)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 3, 5)

insert into table(name, segment_name, status, x_pos, y_pos) values ('1f', 'Balkon', 'Exists', 3, 6)
insert into table(name, segment_name, status, x_pos, y_pos) values ('2f', 'Balkon', 'Exists', 3, 7)
insert into table(name, segment_name, status, x_pos, y_pos) values ('16', 'Nepusacki', 'Exists', 4, 0)
insert into table(name, segment_name, status, x_pos, y_pos) values ('17', 'Nepusacki', 'Exists', 4, 1)
insert into table(name, segment_name, status, x_pos, y_pos) values ('18', 'Nepusacki', 'Exists', 4, 2)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 4, 3)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 4, 4)
insert into table(name, segment_name, status, x_pos, y_pos) values ('', '', 'Not Exists', 4, 5)
insert into table(name, segment_name, status, x_pos, y_pos) values ('3f', 'Balkon', 'Exists', 4, 6)
insert into table(name, segment_name, status, x_pos, y_pos) values ('4f', 'Balkon', 'Exists', 4, 7)



insert into segment_table(segment_id, table_id) values(1,1)
insert into segment_table(segment_id, table_id) values(1,2)
insert into segment_table(segment_id, table_id) values(1,3)
insert into segment_table(segment_id, table_id) values(1,4)
insert into segment_table(segment_id, table_id) values(1,5)
insert into segment_table(segment_id, table_id) values(1,6)
insert into segment_table(segment_id, table_id) values(1,7)
insert into segment_table(segment_id, table_id) values(1,8)
insert into segment_table(segment_id, table_id) values(1,9)
insert into segment_table(segment_id, table_id) values(1,10)

insert into segment_table(segment_id, table_id) values(1,11)
insert into segment_table(segment_id, table_id) values(1,12)
insert into segment_table(segment_id, table_id) values(1,13)
insert into segment_table(segment_id, table_id) values(1,14)
insert into segment_table(segment_id, table_id) values(1,15)
insert into segment_table(segment_id, table_id) values(1,16)
insert into segment_table(segment_id, table_id) values(1,17)
insert into segment_table(segment_id, table_id) values(1,18)
insert into segment_table(segment_id, table_id) values(1,19)
insert into segment_table(segment_id, table_id) values(1,20)

insert into segment_table(segment_id, table_id) values(1,21)
insert into segment_table(segment_id, table_id) values(1,22)
insert into segment_table(segment_id, table_id) values(1,23)
insert into segment_table(segment_id, table_id) values(1,24)
insert into segment_table(segment_id, table_id) values(1,25)
insert into segment_table(segment_id, table_id) values(1,26)
insert into segment_table(segment_id, table_id) values(1,27)
insert into segment_table(segment_id, table_id) values(1,28)
insert into segment_table(segment_id, table_id) values(1,29)
insert into segment_table(segment_id, table_id) values(1,30)

insert into segment_table(segment_id, table_id) values(1,31)
insert into segment_table(segment_id, table_id) values(1,32)
insert into segment_table(segment_id, table_id) values(1,33)
insert into segment_table(segment_id, table_id) values(1,34)
insert into segment_table(segment_id, table_id) values(1,35)
insert into segment_table(segment_id, table_id) values(1,36)
insert into segment_table(segment_id, table_id) values(1,37)
insert into segment_table(segment_id, table_id) values(1,38)
insert into segment_table(segment_id, table_id) values(1,39)
insert into segment_table(segment_id, table_id) values(1,40)


INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (1,1);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (4,2);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (10,3);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (4,4);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (15,5);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (1,7);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (2,8);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (3,9);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (4,10);
INSERT INTO TABLE_RESERVATION (TABLE_ID, RESERVATION_ID) VALUES (15,11);




INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (4,2);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (10,3);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (4,5);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (1,1);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (8,4);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (15,6);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (1,8);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (2,9);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (3,10);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (4,11);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (15,12);
INSERT INTO ORDER_TABLE (TABLE_ID, ORDER_ID) VALUES (15,13);


INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (1,1)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (1,2)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (1,3)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (1,4)

INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (2,6)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (2,7)

INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (3,17)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (3,18)

INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (4,21)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (4,22)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (4,1)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (4,2)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (4,3)
INSERT INTO WAITER_TABLES (WAITER_ID,TABLE_ID) VALUES (4,4)

INSERT INTO CHANGED_SHIFT_COOK (DATE, COOK1_COOK_ID,COOK2_COOK_ID) VALUES ('2017-3-3',1,2)

INSERT INTO RESTAURANT_CHANGED_SHIFTS_COOKS (RESTAURANT_ID,SHIFT_ID) VALUES (1,1)

INSERT INTO CHANGED_SHIFT_BARTENDER (DATE, BARTENDER1_BARTENDER_ID,BARTENDER2_BARTENDER_ID) VALUES ('2017-3-3',1,2)
INSERT INTO RESTAURANT_CHANGED_SHIFTS_BARTENDERS (RESTAURANT_ID,SHIFT_ID) VALUES (1,1)

INSERT INTO CHANGED_SHIFT_WAITER (DATE, WAITER1_WAITER_ID,WAITER2_WAITER_ID) VALUES ('2017-2-23',1,3)
INSERT INTO CHANGED_SHIFT_WAITER (DATE, WAITER1_WAITER_ID,WAITER2_WAITER_ID) VALUES ('2017-2-25',1,3)

INSERT INTO RESTAURANT_CHANGED_SHIFTS_WAITERS (RESTAURANT_ID,SHIFT_ID) VALUES (1,1)
INSERT INTO RESTAURANT_CHANGED_SHIFTS_WAITERS (RESTAURANT_ID,SHIFT_ID) VALUES (1,2)


INSERT INTO RATE_RESTAURANT (RATE) VALUES (4)
INSERT INTO RATE_RESTAURANT (RATE) VALUES (3)
INSERT INTO RATE_RESTAURANT (RATE) VALUES (2)
INSERT INTO RATE_RESTAURANT (RATE) VALUES (4)
INSERT INTO RATE_RESTAURANT (RATE) VALUES (5)
INSERT INTO RATE_RESTAURANT (RATE) VALUES (5)

INSERT INTO RATE_RESTAURANT_GUEST (GUEST_ID, RATE_RESTAURANT_ID) VALUES (1,2)
INSERT INTO RATE_RESTAURANT_GUEST (GUEST_ID, RATE_RESTAURANT_ID) VALUES (2,3)
INSERT INTO RATE_RESTAURANT_GUEST (GUEST_ID, RATE_RESTAURANT_ID) VALUES (3,4)
--INSERT INTO RATE_RESTAURANT_GUEST (GUEST_ID, RATE_RESTAURANT_ID) VALUES (4,6)

INSERT INTO RESTAURANT_RATES (RESTAURANT_ID, RATE_RESTAURANT_ID) VALUES (1,2)
INSERT INTO RESTAURANT_RATES (RESTAURANT_ID, RATE_RESTAURANT_ID) VALUES (1,3)
INSERT INTO RESTAURANT_RATES (RESTAURANT_ID, RATE_RESTAURANT_ID) VALUES (1,4)
--INSERT INTO RESTAURANT_RATES (RESTAURANT_ID, RATE_RESTAURANT_ID) VALUES (1,6)
INSERT INTO RESTAURANT_RATES (RESTAURANT_ID, RATE_RESTAURANT_ID) VALUES (1,1)


INSERT INTO RATE_ORDER (RATE) VALUES (5)
INSERT INTO RATE_ORDER (RATE) VALUES (3)
INSERT INTO RATE_ORDER (RATE) VALUES (4)
INSERT INTO RATE_ORDER (RATE) VALUES (2)
INSERT INTO RATE_ORDER (RATE) VALUES (3)
INSERT INTO RATE_ORDER (RATE) VALUES (4)

INSERT INTO RATE_ORDER_GUEST (GUEST_ID, RATE_ORDER_ID) VALUES (1,1)
INSERT INTO RATE_ORDER_GUEST (GUEST_ID, RATE_ORDER_ID) VALUES (1,2)
INSERT INTO RATE_ORDER_GUEST (GUEST_ID, RATE_ORDER_ID) VALUES (2,4)
INSERT INTO RATE_ORDER_GUEST (GUEST_ID, RATE_ORDER_ID) VALUES (3,3)

INSERT INTO ORDER_RATES (ORDER_ID, RATE_ORDER_ID) VALUES (1,1)
INSERT INTO ORDER_RATES (ORDER_ID, RATE_ORDER_ID) VALUES (2,3)
INSERT INTO ORDER_RATES (ORDER_ID, RATE_ORDER_ID) VALUES (3,2)
INSERT INTO ORDER_RATES (ORDER_ID, RATE_ORDER_ID) VALUES (3,4)

INSERT INTO DISH_NUM_RATE (DISH_DISH_ID, NUM_RATE) VALUES (1,5)
INSERT INTO DISH_NUM_RATE (DISH_DISH_ID, NUM_RATE) VALUES (2,5)
INSERT INTO DISH_NUM_RATE (DISH_DISH_ID, NUM_RATE) VALUES (2,4)
INSERT INTO DISH_NUM_RATE (DISH_DISH_ID, NUM_RATE) VALUES (3,5)
INSERT INTO DISH_NUM_RATE (DISH_DISH_ID, NUM_RATE) VALUES (3,3)
INSERT INTO DISH_NUM_RATE (DISH_DISH_ID, NUM_RATE) VALUES (3,2)


INSERT INTO RATE_SERVICE (RATE) VALUES (3)
INSERT INTO RATE_SERVICE (RATE) VALUES (4)
INSERT INTO RATE_SERVICE (RATE) VALUES (4)
INSERT INTO RATE_SERVICE (RATE) VALUES (3)
INSERT INTO RATE_SERVICE (RATE) VALUES (5)
INSERT INTO RATE_SERVICE (RATE) VALUES (2)

INSERT INTO RATE_SERVICE_GUEST (GUEST_ID, RATE_SERVICE_ID) VALUES (1,1)
INSERT INTO RATE_SERVICE_GUEST (GUEST_ID, RATE_SERVICE_ID) VALUES (1,2)
INSERT INTO RATE_SERVICE_GUEST (GUEST_ID, RATE_SERVICE_ID) VALUES (2,3)
INSERT INTO RATE_SERVICE_GUEST (GUEST_ID, RATE_SERVICE_ID) VALUES (3,4)
INSERT INTO RATE_SERVICE_GUEST (GUEST_ID, RATE_SERVICE_ID) VALUES (4,5)

INSERT INTO ORDER_RATE_SERVICE (ORDER_ID, RATE_SERVICE_ID) VALUES (1,1)
INSERT INTO ORDER_RATE_SERVICE (ORDER_ID, RATE_SERVICE_ID) VALUES (1,2)
INSERT INTO ORDER_RATE_SERVICE (ORDER_ID, RATE_SERVICE_ID) VALUES (5,5)
INSERT INTO ORDER_RATE_SERVICE (ORDER_ID, RATE_SERVICE_ID) VALUES (3,3)
INSERT INTO ORDER_RATE_SERVICE (ORDER_ID, RATE_SERVICE_ID) VALUES (8,4)

INSERT INTO WAITER_NUM_RATE (WAITER_WAITER_ID, NUM_RATE) VALUES (1,3)
INSERT INTO WAITER_NUM_RATE (WAITER_WAITER_ID, NUM_RATE) VALUES (1,4)
INSERT INTO WAITER_NUM_RATE (WAITER_WAITER_ID, NUM_RATE) VALUES (1,4)
INSERT INTO WAITER_NUM_RATE (WAITER_WAITER_ID, NUM_RATE) VALUES (1,5)
INSERT INTO WAITER_NUM_RATE (WAITER_WAITER_ID, NUM_RATE) VALUES (3,3)


