CREATE TABLE 'admin' (
                       'ADMIN_ID'             INT NOT NULL,
                       'ADMIN_NAME'           VARCHAR(255) NOT NULL,
                       'ADMIN_EMAIL'          VARCHAR(255) NOT NULL,
                       'PASSWORD'             VARCHAR(255) NOT NULL,
                       'CREATED_DATE'         TIMESTAMP NOT NULL,
                       'LAST_MODIFIED_DATE'   TIMESTAMP NOT NULL,
                       PRIMARY KEY (ADMIN_ID)
);


CREATE TABLE DELIVERY_INFO (
                               DELIVERY_ID          INT NOT NULL,
                               DELIVERY_TIME        TIME NOT NULL,
                               ADDITIONAL_CHARGES   DECIMAL(10,2) NOT NULL,
                               CREATED_DATE         TIMESTAMP NOT NULL,
                               LAST_MODIFIED_DATE   TIMESTAMP NOT NULL,
                               PRIMARY KEY (DELIVERY_ID)
);


CREATE TABLE GROUP_ORDER (
                             GROUP_ORDER_ID       INT NOT NULL,
                             RESTAURANT_FK        INT NOT NULL,
                             GROUP_ORDER_URL      VARCHAR(255) NOT NULL,
                             GROUP_ORDER_TIMEOUT  INT NOT NULL,
                             CREATED_DATE         TIMESTAMP NOT NULL,
                             LAST_MODIFIED_DATE   TIMESTAMP NOT NULL,
                             PRIMARY KEY (GROUP_ORDER_ID)
);


CREATE TABLE ITEM (
                      ITEM_ID              INT NOT NULL,
                      GROUP_ORDER_FK       INT NOT NULL,
                      ITEM_NAME            VARCHAR(255) NOT NULL,
                      EMPLOYEE_NAME        CHAR(10),
                      CREATED_DATE         TIMESTAMP NOT NULL,
                      LAST_MODIFIED_DATE   TIMESTAMP NOT NULL,
                      PRIMARY KEY (ITEM_ID)
);


CREATE TABLE RESTAURANT (
                            RESTAURANT_ID        INT NOT NULL,
                            DELIVERY_FK          INT NOT NULL,
                            RESTAURANT_NAME      VARCHAR(255) NOT NULL,
                            ADDRESS              VARCHAR(255) NOT NULL,
                            PHONE_NUMBER         VARCHAR(15) NOT NULL,
                            MENU_URL             VARCHAR(255) NOT NULL,
                            CREATED_DATE         TIMESTAMP NOT NULL,
                            LAST_MODIFIED_DATE   TIMESTAMP NOT NULL,
                            PRIMARY KEY (RESTAURANT_ID)
);


ALTER TABLE GROUP_ORDER
    ADD CONSTRAINT FK_INCLUDE
        FOREIGN KEY (RESTAURANT_FK)
            REFERENCES RESTAURANT (RESTAURANT_ID)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;


ALTER TABLE ITEM
    ADD CONSTRAINT FK_HAVE
        FOREIGN KEY (GROUP_ORDER_FK)
            REFERENCES GROUP_ORDER (GROUP_ORDER_ID)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;


ALTER TABLE RESTAURANT
    ADD CONSTRAINT FK_CONTAINTS
        FOREIGN KEY (DELIVERY_FK)
            REFERENCES DELIVERY_INFO (DELIVERY_ID)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;


