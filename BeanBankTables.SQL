 --DROP TABLES FIRST JUST IN CASE--
DROP TABLE BeanBankAccounts;
DROP TABLE BeanBankUsers;
DROP SEQUENCE BeanBankSequence; --No arguments.

CREATE SEQUENCE BeanBankSequence START WITH 1 INCREMENT BY 1;

CREATE TABLE BeanBankUsers
(
    userID    NUMBER(10)    PRIMARY KEY,
    username  VARCHAR2(50)  UNIQUE NOT NULL,
    password  VARCHAR2(50)  NOT NULL,
    superuser NUMBER(1)     NOT NULL,
    fullName  VARCHAR2(50)
);

CREATE TABLE BeanBankAccounts
(
    accountID NUMBER(10)     PRIMARY KEY,
    balance   NUMBER(20, 2)  NOT NULL,
    userID    NUMBER(10)     --REFERENCES BeanBankUsers(userID) ON DELETE CASCADE
);

ALTER TABLE BeanBankAccounts ADD CONSTRAINT fk_BeanBankAccounts_BeanBankUsers FOREIGN KEY --Foreign key on Accounts that references Users
(userID) REFERENCES BeanBankUsers(userID) ON DELETE CASCADE;

CREATE OR REPLACE PROCEDURE createAccount(inputBalance VARCHAR2, inputUserID NUMBER) IS
BEGIN

INSERT INTO BeanBankAccounts VALUES (BeanBankSequence.NEXTVAL, inputBalance, inputUserID);

END;
/

INSERT INTO BeanBankUsers VALUES(9989, 'admin', 'admin', 1, 'MinnowTheAdmin');
INSERT INTO BeanBankUsers VALUES(529, 'GEC', 'a', 0, 'Greg Campbell');
INSERT INTO BeanBankUsers VALUES(990, 'a', 'a', 0, 'aNonymous');

INSERT INTO BeanBankAccounts VALUES(19989, 9887652, 9989);
INSERT INTO BeanBankAccounts VALUES(1529, 1000000000000.99, 529);
INSERT INTO BeanBankAccounts VALUES(1990, 0, 990);

SELECT * FROM BeanBankUsers;
SELECT * FROM BeanBankAccounts;

COMMIT;