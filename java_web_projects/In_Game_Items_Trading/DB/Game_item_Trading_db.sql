create database game_items_trading;
drop database game_items_trading;
use game_items_trading;
CREATE TABLE GameItems(
	id INT not null auto_increment,
    primary key(id),
	skin_name VARCHAR(50),
	item_name VARCHAR(50),
	type VARCHAR(50),
	rarity VARCHAR(50),
    exterior varchar(50),
    img varchar(50)
);
CREATE TABLE GameAccount(
	id INT not null auto_increment,
    primary key(id),
	username VARCHAR(50),
	password VARCHAR(50),
	dob DATE, /*du 18 tuoi*/
	email VARCHAR(100),
	gender VARCHAR(10),
	avatar VARCHAR(50)
);
CREATE TABLE UserItem( -- save items in possession of each account --  
	id INT not null auto_increment,
    primary key(id),
    game_account_id int,
    item_id int,
	foreign key(game_account_id) references GameAccount(id),
	foreign key(item_id) references GameItems(id)
);
CREATE TABLE Role(
	id INT not null auto_increment,
    primary key(id),
	role_name VARCHAR(50)
);
CREATE TABLE UserAccount(
	id int not null auto_increment,
    primary key(id),
	username VARCHAR(50),
	password VARCHAR(50),
	game_account_id int,
    role_id int,
    money_amount double,
	foreign key(game_account_id) references GameAccount(id),
	foreign key(role_id) references Role(id)
);
CREATE TABLE Notification (
    id INT NOT NULL auto_increment,
    PRIMARY KEY (id),
    date date,
    user_id INT,
    FOREIGN KEY (user_id)
        REFERENCES UserAccount (id),
    noti_content VARCHAR(50),
    img VARCHAR(50)
);
create table MarketItems(-- show item on market-- 
	id int not null auto_increment,
    primary key(id),
    user_id int,
    foreign key(user_id) references UserAccount(id),
    item_id int,
    foreign key(item_id) references GameItems(id)
);
create table TradeItems(
	id int not null auto_increment,
    primary key(id),
    give_id int,
    foreign key(give_id) references useritem(id),
    rec_id int,
    foreign key(rec_id) references gameitems(id)
);
create table PaymentRequest ( -- adminscreen --
	id int not null auto_increment,
    primary key(id),
    user_id int,
    money double,
    date date,
    foreign key(user_id) references UserAccount(id),
    img varchar(50)
);
create table RetrieveRequest(
	id int not null auto_increment,
    primary key (id),
    user_id int,
    date date,
    foreign key(user_id) references UserAccount(id),
    retrieve_amount double,
    bank_details varchar(50) 
);

