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


INSERT INTO RESTAURANT (NAME,COUNTRY,CITY,STREET,NUMBER) VALUES('restaurant1','Serbia','Novi Sad','Fruskogorska',15);
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
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT) VALUES('Kola3',110,'sok',60)

INSERT INTO DISH (NAME,PRICE,TEXT,COUNT) VALUES('Jelo1',110,'jelo',60)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT) VALUES('Jelo2',110,'jelo',62)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT) VALUES('Jelo3',110,'jelo',60)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT) VALUES('Jelo4',110,'jelo',64)


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

