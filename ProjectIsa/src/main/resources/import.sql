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


INSERT INTO RESTAURANT (NAME,COUNTRY,CITY,STREET,NUMBER) VALUES('restaurant1','Serbia','Novi Sad','Bulervar oslobodjenja',25);
INSERT INTO RESTAURANT (NAME,COUNTRY,CITY,STREET,NUMBER) VALUES('restaurant2','Serbia','Novi Sad','Fruskogorska',17);

INSERT INTO RESTAURANT_MANAGERS_OF_RESTAURANT (RESTAURANT_ID,RESTAURANT_MANAGER_ID) VALUES (1,1)
INSERT INTO RESTAURANT_MANAGERS_OF_RESTAURANT (RESTAURANT_ID,RESTAURANT_MANAGER_ID) VALUES (2,2)


INSERT INTO WAITER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('9','9','milanko','milanovic','1','M','no45')

INSERT INTO COOK (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('8','8','milance','milanovic','1','M','no46')
INSERT INTO COOK (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('8@8','8','milance','milanovic','1','M','no46')

INSERT INTO BARTENDER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('a@gmail.com','a','milan','milanovic','1','M','no45')
INSERT INTO BARTENDER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('saAA@ss','passw','milan','milanovic','1','M','no45')


INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('6','6','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder1@gmail.com','1','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder2@gmail.com','1','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder3@gmail.com','1','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder4@gmail.com','1','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder5@gmail.com','1','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder6@gmail.com','1','mika','mikic',1);
INSERT INTO BIDDER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('bidder7@gmail.com','1','mika','mikic',1);


INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Kola1',100,'sok',50)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Kola2',105,'sok',40)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Pivo',150,'pivo',60)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Kola3',110,'sok',60)


INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH) VALUES('Jelo1',110,'jelo',60,'salad')
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH) VALUES('Jelo2',110,'jelo',62,'baked')
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH) VALUES('Jelo3',110,'jelo',60,'salad')
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,TYPE_OF_DISH) VALUES('Jelo4',110,'jelo',64,'cooked')


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

INSERT INTO ORDERR () VALUES()
INSERT INTO ORDERR () VALUES()
INSERT INTO ORDERR () VALUES()
INSERT INTO ORDERR () VALUES()

INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (1,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (2,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (3,3)

INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (1,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (2,2)
INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (3,3)

INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (2,1)
INSERT INTO RESTAURANT_ORDER (RESTAURANT_ID, ORDER_ID) VALUES (2,2)

INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,2)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,3)

INSERT INTO BARTENDER_ORDERS (BARTENDER_ID, ORDER_ID) VALUES (1,2)
INSERT INTO BARTENDER_ORDERS (BARTENDER_ID, ORDER_ID) VALUES (1,3)

INSERT INTO COOK_ORDERS (COOK_ID, ORDER_ID) VALUES (1,2)
INSERT INTO COOK_ORDERS (COOK_ID, ORDER_ID) VALUES (1,3)


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


INSERT INTO RESTAURANT_ORDERR (ORDER_ACTIVE, START_DATE,END_DATE,DISH_DISH_ID,COUNT) VALUES ('open','2016-11-11','2017-11-11',1,30)
INSERT INTO RESTAURANT_ORDERR (ORDER_ACTIVE, START_DATE,END_DATE,DISH_DISH_ID,COUNT) VALUES ('open','2016-11-11','2017-11-11',2,30)


INSERT INTO OFFER (PRICE, BIDDER_BIDDER_ID,ACCEPTED) VALUES (60,1,'in progress')
INSERT INTO OFFER (PRICE, BIDDER_BIDDER_ID,ACCEPTED) VALUES (30,2,'in progress')
INSERT INTO OFFER (PRICE, BIDDER_BIDDER_ID,ACCEPTED) VALUES (30,1,'in progress')


INSERT INTO RESTAURANT_OFFER (RESTAURANT_ORDER_ID, OFFER_ID) VALUES (1,1)
INSERT INTO RESTAURANT_OFFER (RESTAURANT_ORDER_ID, OFFER_ID) VALUES (1,2)


INSERT INTO RESTAURANT_RESTAURANT_ORDER (RESTAURANT_ID,RESTAURANT_ORDER_ID) VALUES (1,1)
INSERT INTO RESTAURANT_RESTAURANT_ORDER (RESTAURANT_ID,RESTAURANT_ORDER_ID) VALUES (1,2)

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












