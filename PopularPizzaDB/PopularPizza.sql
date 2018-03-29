-- PopularPizza.sql
-- Popular Pizza Database - Comp1140 A03
-- Author: Cody Lewis - c3283349
-- Date:   18-OCT-2017
-- Last Modified: 20-OCT-2017
-- Description:
-- The SQL script for the Database for Popular Pizza
-- and the SQL script for the queries for Popular Pizza

DROP TABLE OrderStatus;
DROP TABLE Phone;
DROP TABLE Orders;
DROP TABLE Customer;
DROP TABLE Address;
DROP TABLE DiscountProgram;
DROP TABLE SupplyIngredients;
DROP TABLE IngredientOrders;
DROP TABLE Supplier;
DROP TABLE MenuItemIngredient;
DROP TABLE OrderMenuItem;
DROP TABLE Ingredient;
DROP TABLE MenuItem;
DROP TABLE DeliveryDriver;
DROP TABLE Instore;
DROP TABLE Employee;
GO
-- Employee table
CREATE TABLE Employee(
  EmployeeID    CHAR(10) PRIMARY KEY, -- Employee id code
  FirstName     VARCHAR(50), -- name
  LastName      VARCHAR(50),
  PostCode      CHAR(4),     -- Address
  Street        VARCHAR(60),
  sNumber       VARCHAR(5),
  TaxFileNumber VARCHAR(9) UNIQUE,  -- Bank details
  AN            VARCHAR(10) UNIQUE,
  BankName      VARCHAR(40),
  BankCode      CHAR(6),
  Payrate       DECIMAL(4,2)
);
-- Delivery Driver table
CREATE TABLE DeliveryDriver(
  EmployeeID    CHAR(10) PRIMARY KEY,
  DeliveriesNo  INT DEFAULT 0,           -- total Deliveries made by the Employee
  FOREIGN KEY(EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE NO ACTION
);
-- Instore Employee table
CREATE TABLE Instore(
  EmployeeID    CHAR(10) PRIMARY KEY,
  Hours         DECIMAL(5,2) DEFAULT 0,         -- Hours worked within the month
  FOREIGN KEY(EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE NO ACTION
);
-- Address Table
CREATE TABLE Address(
  PostCode  CHAR(4) PRIMARY KEY,
  Suburb    VARCHAR(60) NOT NULL -- Suburbs do not have to be UNIQUE to have different post codes
);
-- The discount program table
CREATE TABLE DiscountProgram(
  Code          CHAR(10) PRIMARY KEY, -- id code
  Description   VARCHAR(1200),  -- discribes the program
  StartDate     DATE DEFAULT GETDATE(), -- dates
  EndDate       DATE,
  Percentage    DECIMAL(3,2), -- The deduction, to be multiplied by the order total
  Requirements  VARCHAR(150)
);
-- Supplier table
CREATE TABLE Supplier(
  SupplierNo    CHAR(10) PRIMARY KEY, -- id code
  Name          VARCHAR(25),  -- company name
  PostCode      CHAR(4) NOT NULL, -- contact deatails
  Street        VARCHAR(60),
  sNumber       VARCHAR(5),
  Phone         CHAR(10) UNIQUE NOT NULL,
  ContactPerson VARCHAR(100)
);
-- Ingredient table
CREATE TABLE Ingredient(
  Code              CHAR(10) PRIMARY KEY, -- id code
  Name              VARCHAR(25) NOT NULL,
  Type              CHAR(1),  -- Meat, vegetable, cheese, etc.
  Description       VARCHAR(1200),
  CurrentStockLevel INT DEFAULT 0,  -- Stock details
  LastStockTake     DATETIME DEFAULT GETDATE(),
  SuggestedStock    INT DEFAULT 0,
  Price             DECIMAL(5,2)
);
-- Menu item table
CREATE TABLE MenuItem(
  Code                CHAR(10) PRIMARY KEY, -- id code
  Name                VARCHAR(25) NOT NULL,
  Size                CHAR(1),
  CurrentSellingPrice DECIMAL(5,2)
);
-- Customer table
CREATE TABLE Customer(
  ID        CHAR(10) PRIMARY KEY, --The Customer's id code for the shop
  Name      VARCHAR(100), -- Name of Customer,
  Status    CHAR(1),       -- The status of the Customer M for member, N for not member
  PhoneNo   CHAR(10) UNIQUE NOT NULL, -- contact details
  PostCode  CHAR(4),
  Street    VARCHAR(60),
  sNumber   VARCHAR(5)
);
-- Order Table
CREATE TABLE Orders(  -- Named Orders as order is a reserved keyword
  OrderCode   CHAR(10) PRIMARY KEY, -- id code
  StaffID     CHAR(10) NOT NULL,
  CustomerID  CHAR(10) NOT NULL,
  DateOfOrder DATE DEFAULT GETDATE(),
  Discount    CHAR(10), -- The discount code
  TaxTotalDue DECIMAL(5,2),
  OrderType   CHAR(1),  -- I=instore D=delivery
  Description VARCHAR(1200),
  FOREIGN KEY(CustomerID) REFERENCES Customer(ID) ON UPDATE CASCADE ON DELETE NO ACTION,
  FOREIGN KEY(StaffID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE NO ACTION
);
-- The order's status table
CREATE TABLE OrderStatus(
  OrderID       CHAR(10) PRIMARY KEY,
  Status        CHAR(1),  -- C = completed, O = ongoing
  PaymentMethod CHAR(1),  -- C=card M=cash
  FOREIGN KEY(OrderID) REFERENCES Orders(OrderCode) ON UPDATE CASCADE ON DELETE NO ACTION
);
-- Phone order table
CREATE TABLE Phone(
  OrderCode   CHAR(10) PRIMARY KEY,
  AnswerTime  DATETIME,
  HangupTime  DATETIME,
  HoaxRecord  BIT,
  FOREIGN KEY(OrderCode) REFERENCES Orders(OrderCode) ON UPDATE CASCADE ON DELETE NO ACTION,
);
-- Ingredient orders table
CREATE TABLE IngredientOrders(
  OrderNo         CHAR(10) PRIMARY KEY,
  DateOrdered     DATE DEFAULT GETDATE(),
  TotalAmount     DECIMAL(8,2), -- Total price plus tax
  Description     VARCHAR(1200),
);
-- Table for the relation Supplier supplies Ingredient
CREATE TABLE SupplyIngredients(
  OrderNo         CHAR(10) PRIMARY KEY,
  SupplierNo      CHAR(10) NOT NULL,
  IngredientID    CHAR(10) NOT NULL,
  Price           DECIMAL(8,2), -- price before tax
  Quantity        INT DEFAULT 0,
  FOREIGN KEY(OrderNo) REFERENCES IngredientOrders(OrderNo) ON UPDATE CASCADE ON DELETE NO ACTION
);
-- Menu Item makes Ingredient many to many relation table
CREATE TABLE MenuItemIngredient( -- This many to many relation must not have a key
  MenuItemID    CHAR(10),        -- The contained values are not UNIQUE
  IngredientID  CHAR(10),
  Quantity      INT DEFAULT 0
);
-- Menu item make Order many to many relation table
CREATE TABLE OrderMenuItem( -- The values in this relation are also not UNIQUE
  OrderID       CHAR(10),
  MenuItemID    CHAR(10),
  Quantity      INT DEFAULT 0
);
GO
-- Input Data
-- Loading to Employee table
INSERT INTO Employee VALUES ('0000000001','John','Smith','0000','Wallaby St.','1','000000001','0000000001','Westpac','000000',25.00);
INSERT INTO Employee VALUES ('0000000002','Jane','Sully0000000112','0000','Vallery Rd.','2','000000002','0000000002','Westpac','000000',35.00);
INSERT INTO Employee VALUES ('0000000003','Christoffer','Colbert','1234','Kook Bld.','20','000000003','0000000003','Commbank','000002',30.00);
INSERT INTO Employee VALUES ('0000000004','Kazimir','Taylor','4325','Hoenn St.','300','000000004','0000000004','Commbank','000002',20.00);
INSERT INTO Employee VALUES ('0000000005','Leif','Girish','4325','Hoenn St.','256','000000005','0000000005','St. George','000003',30.00);
INSERT INTO Employee VALUES ('0000000006','Marika O','Siridaein','6226','Kanto Rd.','151','000000006','0000000006','Commbank','000002',26.96);
GO
-- Loading to DeliveryDriver table
INSERT INTO DeliveryDriver VALUES ('0000000003',50);
INSERT INTO DeliveryDriver VALUES ('0000000002',60);
INSERT INTO DeliveryDriver VALUES ('0000000006',30);
GO
-- Loading to Instore table
INSERT INTO Instore VALUES ('0000000005',50.00);
INSERT INTO Instore VALUES ('0000000001',40.50);
INSERT INTO Instore VALUES ('0000000004',30.25);
GO
-- Loading to Address Table
INSERT INTO Address VALUES ('0000','Waratuka');
INSERT INTO Address VALUES ('1234','Valley Plane');
INSERT INTO Address VALUES ('4321','Anacoco');
INSERT INTO Address VALUES ('4325','Charlotte');
INSERT INTO Address VALUES ('6226','Karnak');
INSERT INTO Address VALUES ('2222','Halstead');
INSERT INTO Address VALUES ('3535','Hickory Corners');
GO
-- Loading to discount program table
INSERT INTO DiscountProgram VALUES ('0000000011','Christmas Discount','2017-NOV-30','2018-JAN-01',0.90,'It is near Christmas');
INSERT INTO DiscountProgram VALUES ('0000000012','Member Discount','2017-SEP-30','2020-JAN-01',0.80,'The person redeeming it is a member');
INSERT INTO DiscountProgram VALUES ('0000000013','Ad Discount','2017-NOV-30','2019-MAR-26',0.75,'The person redeems the coupon from our ad');
GO
-- Loading to Supplier table
INSERT INTO Supplier VALUES ('0000000111','Grants Suppliers','1234','Gnoot Bld.','20','0412345678','Xanthe Kovachev');
INSERT INTO Supplier VALUES ('0000000112','Stockmire','4321','Sire St.','42','0487654321','Suleyman Renaud');
INSERT INTO Supplier VALUES ('0000000113','Gustins Food Stocks','4325','Statagem St.','25','0483234543','Liisi Garnett');
GO
-- Loading to Ingredient table
INSERT INTO Ingredient VALUES ('0000001111','Pepperoni','M','The pork based topping for Pizza',200,'2017-OCT-18',250,5.00);
INSERT INTO Ingredient VALUES ('0000001112','Tomato','V','The fruit used for a variety of toppings for Pizza',300,'2017-OCT-10',400,2.00);
INSERT INTO Ingredient VALUES ('0000001113','Anchovies','F','The fish topping for Pizza',100,'2017-OCT-17',130,3.50);
INSERT INTO Ingredient VALUES ('0000001114','Pizza Base','V','The ingredients for the base of the pizza',500,'2017-OCT-11',700,1.50);
INSERT INTO Ingredient VALUES ('0000001115','Mozzerella','C','The cheese topping for Pizza',500,'2017-OCT-09',700,5.00);
INSERT INTO Ingredient VALUES ('0000001116','Basil','V','The vegetable topping for Pizza',100,'2017-OCT-16',130,1.00);
INSERT INTO Ingredient VALUES ('0000001117','Zucchini','V','The vegetable topping for Pizza',50,'2017-OCT-16',70,5.00);
INSERT INTO Ingredient VALUES ('0000001118','Spinich','M','The vegetable topping for Pizza',30,'2017-OCT-18',50,5.55);
GO
-- Loading to Menu item table
INSERT INTO MenuItem VALUES ('0000011111','Pepperoni Pizza','M',20.00);
INSERT INTO MenuItem VALUES ('0000011112','Pepperoni Pizza','L',30.00);
INSERT INTO MenuItem VALUES ('0000011121','Margarita Pizza','M',20.00);
GO
-- Loading to Menu item ingredient relation table
INSERT INTO MenuItemIngredient VALUES ('0000011111','0000001111',2); -- Medium Pepperoni pizza Pepperoni
INSERT INTO MenuItemIngredient VALUES ('0000011111','0000001112',1); -- Medium Pepperoni pizza Tomato
INSERT INTO MenuItemIngredient VALUES ('0000011111','0000001115',1); -- Medium Pepperoni pizza Mozzerella
INSERT INTO MenuItemIngredient VALUES ('0000011111','0000001114',1); -- Medium Pepperoni pizza base
INSERT INTO MenuItemIngredient VALUES ('0000011112','0000001111',3); -- Large Pepperoni pizza Pepperoni
INSERT INTO MenuItemIngredient VALUES ('0000011112','0000001112',2); -- Large Pepperoni pizza Tomato
INSERT INTO MenuItemIngredient VALUES ('0000011112','0000001115',2); -- Large Pepperoni pizza Mozzerella
INSERT INTO MenuItemIngredient VALUES ('0000011112','0000001114',1); -- Large Pepperoni pizza base
INSERT INTO MenuItemIngredient VALUES ('0000011121','0000001116',3); -- Medium Pepperoni pizza Basil
INSERT INTO MenuItemIngredient VALUES ('0000011121','0000001112',2); -- Medium Pepperoni pizza Tomato
INSERT INTO MenuItemIngredient VALUES ('0000011121','0000001115',2); -- Medium Pepperoni pizza Mozzerella
INSERT INTO MenuItemIngredient VALUES ('0000011121','0000001114',1); -- Medium Pepperoni pizza base
GO
-- Loading to Customer table
INSERT INTO Customer VALUES ('0000111111','Bakar Watts','M','0423388675','6226','Karnak St.','50');
INSERT INTO Customer VALUES ('0000111112','Harmon Lambert','N','0432299857','2222','Halstead Pl.','34');
INSERT INTO Customer VALUES ('0000111113','Linos Frost','M','0433388872','3535','Hickory St.','2350');
GO
-- Loading to Order Table
INSERT INTO Orders VALUES ('0001111111','0000000001','0000111111','2017-OCT-20 15:31',NULL,4.50,'I','General Order');
INSERT INTO Orders VALUES ('0001111112','0000000002','0000111113','2017-OCT-21 17:31',NULL,2.50,'D','Delivery Order');
INSERT INTO Orders VALUES ('0001111113','0000000003','0000111112','2017-OCT-23 16:31',NULL,5.50,'I','Gluten free Order');
INSERT INTO Orders VALUES ('0001111114','0000000003','0000111112','2017-OCT-24 16:40',NULL,9.56,'I','Gluten free Order');
INSERT INTO Orders VALUES ('0001111115','0000000002','0000111113','2017-OCT-23 16:20',NULL,7.30,'I','General Order');
INSERT INTO Orders VALUES ('0001111116','0000000001','0000111111','2017-OCT-23 16:25','0000000013',6.50,'I','Birthday');
INSERT INTO Orders VALUES ('0001111117','0000000001','0000111113','2017-JAN-23 17:01',NULL,5.20,'I','General Order');
INSERT INTO Orders VALUES ('0001111118','0000000003','0000111113','2017-OCT-04 19:32',NULL,11.50,'D','Delivery Order');
INSERT INTO Orders VALUES ('0001111119','0000000003','0000111113','2017-SEP-21 17:47',NULL,2.50,'D','Delivery Order');
INSERT INTO Orders VALUES ('0001111120','0000000002','0000111112','2017-OCT-21 17:31',NULL,2.50,'D','Delivery Order');
INSERT INTO Orders VALUES ('0001111121','0000000003','0000111113','2017-OCT-23 19:32',NULL,11.50,'D','Delivery Order'); -- Just added
INSERT INTO Orders VALUES ('0001111122','0000000003','0000111113','2017-OCT-25 19:32',NULL,11.50,'D','Delivery Order');
INSERT INTO Orders VALUES ('0001111123','0000000003','0000111113','2017-OCT-30 19:32',NULL,11.50,'D','Delivery Order');
INSERT INTO Orders VALUES ('0001111124','0000000003','0000111113','2017-NOV-14 19:32',NULL,11.50,'D','Delivery Order');
INSERT INTO Orders VALUES ('0001111125','0000000003','0000111113','2017-NOV-30 19:32',NULL,11.50,'D','Delivery Order');
GO
-- Loading to the order's status table
INSERT INTO OrderStatus VALUES ('0001111111','C','M');
INSERT INTO OrderStatus VALUES ('0001111112','C','C');
INSERT INTO OrderStatus VALUES ('0001111113','C','C');
INSERT INTO OrderStatus VALUES ('0001111114','O',NULL);
INSERT INTO OrderStatus VALUES ('0001111115','C','M');
INSERT INTO OrderStatus VALUES ('0001111116','C','C');
INSERT INTO OrderStatus VALUES ('0001111117','C','C');
INSERT INTO OrderStatus VALUES ('0001111118','C','M');
INSERT INTO OrderStatus VALUES ('0001111119','C','C');
INSERT INTO OrderStatus VALUES ('0001111120',NULL,NULL);
INSERT INTO OrderStatus VALUES ('0001111121',NULL,NULL);
INSERT INTO OrderStatus VALUES ('0001111122',NULL,NULL);
INSERT INTO OrderStatus VALUES ('0001111123',NULL,NULL);
INSERT INTO OrderStatus VALUES ('0001111124',NULL,NULL);
INSERT INTO OrderStatus VALUES ('0001111125',NULL,NULL);
GO
-- Loading to Order Menu item relation table
INSERT INTO OrderMenuItem VALUES ('0001111111','0000011111',2); -- The profit margin kind of went out of the window
INSERT INTO OrderMenuItem VALUES ('0001111111','0000011112',1); -- luckily this is not a real store
INSERT INTO OrderMenuItem VALUES ('0001111112','0000011111',3);
INSERT INTO OrderMenuItem VALUES ('0001111113','0000011121',4);
INSERT INTO OrderMenuItem VALUES ('0001111114','0000011111',3);
INSERT INTO OrderMenuItem VALUES ('0001111115','0000011112',4);
INSERT INTO OrderMenuItem VALUES ('0001111116','0000011121',3);
INSERT INTO OrderMenuItem VALUES ('0001111117','0000011112',3);
INSERT INTO OrderMenuItem VALUES ('0001111118','0000011112',3);
INSERT INTO OrderMenuItem VALUES ('0001111118','0000011111',5);
INSERT INTO OrderMenuItem VALUES ('0001111119','0000011111',3);
GO
-- Phone order table
INSERT INTO Phone VALUES ('0001111111','2017-OCT-20 14:30','2017-OCT-20 14:32',0);
INSERT INTO Phone VALUES ('0001111112','2017-OCT-21 16:52','2017-OCT-21 16:53',0);
INSERT INTO Phone VALUES ('0001111113','2017-OCT-23 13:31','2017-OCT-20 13:32',0);
INSERT INTO Phone VALUES ('0001111117','2017-JAN-23 16:21','2017-JAN-23 16:23',0);
INSERT INTO Phone VALUES ('0001111118','2017-OCT-04 18:46','2017-OCT-04 18:48',0);
INSERT INTO Phone VALUES ('0001111119','2017-SEP-21 17:05','2017-SEP-21 17:07',0);
INSERT INTO Phone VALUES ('0001111120','2017-OCT-21 16:31','2017-OCT-21 16:32',1);
GO
-- Loading to Ingredient orders table
INSERT INTO IngredientOrders VALUES ('0011111111','2017-NOV-20 17:00',3000.00,'Restock of cheese');
INSERT INTO IngredientOrders VALUES ('0011111112','2017-NOV-19 17:00',5000.00,'Restock of pepperoni');
INSERT INTO IngredientOrders VALUES ('0011111113','2017-NOV-09 17:00',4530.00,'Restock of pizza base materials');
INSERT INTO IngredientOrders VALUES ('0011111114','2017-NOV-22 17:00',4000.00,'Restock of pepperoni');
INSERT INTO IngredientOrders VALUES ('0011111115','2017-NOV-22 17:00',2080.00,'Restock of pizza base materials');
INSERT INTO IngredientOrders VALUES ('0011111116','2018-JAN-20 17:00',300.00,'Restock of cheese');
GO
-- Loading to Ingredient Supplier relation table : orderNO, supplier, ingredient, price(Pre-tax), Quantity
INSERT INTO SupplyIngredients VALUES ('0011111111','0000000112','0000001115',2880.80,500);
INSERT INTO SupplyIngredients VALUES ('0011111112','0000000111','0000001111',4864.45,600);
INSERT INTO SupplyIngredients VALUES ('0011111113','0000000113','0000001114',3887.98,400);
INSERT INTO SupplyIngredients VALUES ('0011111114','0000000112','0000001111',3864.45,600);
INSERT INTO SupplyIngredients VALUES ('0011111115','0000000112','0000001114',2000.25,200);
INSERT INTO SupplyIngredients VALUES ('0011111116','0000000112','0000001115',290.45,50);
GO
-- For staff with ID xxx print first, last name and hourly payrate
SELECT FirstName,LastName,Payrate
FROM   Employee
WHERE  EmployeeID = '0000000001';

-- List ingredient details of menu item named xxx
SELECT i.*
FROM Ingredient AS i,MenuItem AS m,MenuItemIngredient AS mii
WHERE (m.Name LIKE '%Margarita Pizza%') AND (m.Code = mii.MenuItemID AND mii.IngredientID = i.Code);

-- List all order details made by the customer with first name xxx
-- via phone between date yyy and zzz
SELECT o.*
FROM Orders AS o
INNER JOIN Customer AS c ON o.CustomerID = c.ID
INNER JOIN Phone AS p ON p.OrderCode = o.OrderCode
WHERE c.Name LIKE '%Linos%'  AND (o.DateOfOrder > '2017-SEP-20' AND  o.DateOfOrder < '2017-OCT-05');

-- Print the salary paid to delivery driver named xxx in the current month
SELECT e.FirstName,COUNT(o.OrderCode)*e.Payrate AS 'Months Salary'
FROM Employee AS e,Orders AS o,DeliveryDriver AS d
WHERE (e.FirstName LIKE '%Christoffer%' AND e.LastName LIKE '%Colbert%') AND e.EmployeeID = d.EmployeeID
        AND o.StaffID = e.EmployeeID AND o.OrderType = 'D' AND MONTH(o.DateOfOrder) = MONTH(GETDATE())
GROUP BY e.FirstName,e.Payrate;

-- List the menu item that was mostly ordered within the current month
SELECT TOP 1 m.*
FROM MenuItem AS m,OrderMenuItem AS omi,Orders AS o
WHERE (omi.MenuItemID = m.Code AND o.OrderCode = omi.OrderID) AND MONTH(o.DateOfOrder) = MONTH(GETDATE())
GROUP BY omi.MenuItemID, m.Code,m.Name,m.Size,m.CurrentSellingPrice
HAVING SUM(omi.Quantity)>0
ORDER BY SUM(omi.Quantity) DESC;

-- List the ingredients the were supplied by the Supplier with id xxx on date yyy
SELECT s.Name,i.Name
FROM Supplier AS s, Ingredient AS i, IngredientOrders AS io, SupplyIngredients AS si
WHERE s.SupplierNo = '0000000112' AND io.DateOrdered = '2017-NOV-22' AND s.SupplierNo = si.SupplierNo
        AND io.OrderNo = si.OrderNo AND si.IngredientID = i.code;
