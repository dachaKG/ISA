INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('guest1','pass1','mika','mikic','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('guest2','pass2','mika','mikic','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('guest3','pass3','mika','mikic','1');
INSERT INTO GUEST (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('4','4','mika','mikic','1');

--INSERT INTO EMPLOYED (MAIL,PASSWORD,FIRSTNAME,LASTNAME,CLOTHES_SIZE,SHOES_SIZE,REGISTRATED) VALUES ('sasd','passw','milan','milanovic','M','no45',true)

INSERT INTO WAITER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('sa@ss','passw','milan','milanovic','1','M','no45')
--INSERT INTO WAITER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('sa@s','sad','milaffn','milanoviccc',true,'M','no45')

INSERT INTO COOK (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('sa@ss','passw','milan','milanovic','1','M','no45')

INSERT INTO BARTENDER (MAIL,PASSWORD,FIRSTNAME,LASTNAME,REGISTRATED,CLOTHES_SIZE,SHOES_SIZE) VALUES ('saAA@ss','passw','milan','milanovic','1','M','no45')

INSERT INTO BOSS (MAIL, PASSWORD, FIRSTNAME, LASTNAME) VALUES('1','1','mika','mikic');

INSERT INTO SYSTEM_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('s_manager1','manager1','mika','mikic','1');
INSERT INTO SYSTEM_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('s_manager2','manager2','mika','mikic','1');
INSERT INTO SYSTEM_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('s_manager3','manager3','mika','mikic','1');
INSERT INTO SYSTEM_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('2','2','mika','mikic','1');

INSERT INTO RESTAURANT_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('3','3','mika1','mikic','1');
INSERT INTO RESTAURANT_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('4','4','mika2','mikic','1');
INSERT INTO RESTAURANT_MANAGER (MAIL, PASSWORD, FIRSTNAME, LASTNAME, REGISTRATED) VALUES('r_manager3','manager3','mika3','mikic','1');

INSERT INTO RESTAURANT (NAME, RESTAURANT_MANAGER_ID) VALUES('restourant1',1);
INSERT INTO RESTAURANT (NAME, RESTAURANT_MANAGER_ID) VALUES('restourant2',2);

UPDATE RESTAURANT_MANAGER SET RESTAURANT_ID = 1 WHERE RESTAURANT_MANAGER_ID=1;
UPDATE RESTAURANT_MANAGER SET RESTAURANT_ID = 2 WHERE RESTAURANT_MANAGER_ID=2;

INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT,RESTAURANT_ID) VALUES('Kola1',100,'sok',50,1)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT,RESTAURANT_ID) VALUES('Kola2',105,'sok',40,2)
INSERT INTO DRINK (NAME,PRICE,TEXT,COUNT,RESTAURANT_ID) VALUES('Kola3',110,'sok',60,1)

INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,RESTAURANT_ID) VALUES('Jelo1',110,'jelo',60,1)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,RESTAURANT_ID) VALUES('Jelo2',110,'jelo',62,1)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,RESTAURANT_ID) VALUES('Jelo3',110,'jelo',60,1)
INSERT INTO DISH (NAME,PRICE,TEXT,COUNT,RESTAURANT_ID) VALUES('Jelo4',110,'jelo',64,2)

INSERT INTO RESTAURANT_DRINK (RESTAURANT_ID,DRINK_ID) VALUES (1,1)
INSERT INTO RESTAURANT_DRINK (RESTAURANT_ID,DRINK_ID) VALUES (2,2)
INSERT INTO RESTAURANT_DRINK (RESTAURANT_ID,DRINK_ID) VALUES (1,3)

INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (1,1)
INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (1,2)
INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (1,3)
INSERT INTO RESTAURANT_DISH (RESTAURANT_ID,DISH_ID) VALUES (2,4)

INSERT INTO RESTUARANT_WAITERS (RESTAURANT_ID, WAITER_ID) VALUES (1,1)

INSERT INTO ORDERR () VALUES()
INSERT INTO ORDERR () VALUES()
INSERT INTO ORDERR () VALUES()
INSERT INTO ORDERR () VALUES()

INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (1,2)
INSERT INTO ORDER_DRINKS (ORDER_ID, DRINK_ID) VALUES (2,2)

INSERT INTO ORDER_FOOD (ORDER_ID, DISH_ID) VALUES (2,1)

INSERT INTO ORDER_RESTAURANT (ORDER_ID, RESTAURANT_ID) VALUES (1,2)
INSERT INTO ORDER_RESTAURANT (ORDER_ID, RESTAURANT_ID) VALUES (2,1)

INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,1)
INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,2)

INSERT INTO BARTENDER_ORDERS (BARTENDER_ID, ORDER_ID) VALUES (1,1)

INSERT INTO COOK_ORDERS (COOK_ID, ORDER_ID) VALUES (1,2)

INSERT INTO WAITER_RESTAURANT (WAITER_ID, RESTAURANT_ID) VALUES (1,1)

INSERT INTO BARTENDER_RESTAURANT (BARTENDER_ID, RESTAURANT_ID) VALUES (1,1)


--INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,2)
--INSERT INTO WAITER_ORDERS (WAITER_ID, ORDER_ID) VALUES (1,1)

--INSERT INTO WAITER_RESTAURANT(WAITER_ID, RESTAURANT_ID) VALUES (1,1)

