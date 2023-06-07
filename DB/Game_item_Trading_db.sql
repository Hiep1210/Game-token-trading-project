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
    dob DATE, /*du 18 tuoi*/
	email VARCHAR(100),
	gender VARCHAR(10),
	avatar VARCHAR(50),
    role_id int,
    money_amount double,
	foreign key(role_id) references Role(id)
);
CREATE TABLE Notification (
    id INT NOT NULL auto_increment,
    PRIMARY KEY (id),
    date date,
    user_id INT,
    FOREIGN KEY (user_id)
        REFERENCES UserAccount (id),
    noti_content VARCHAR(10000),
    content_type VARCHAR(50)
);
create table MarketItems(-- show item on market-- 
	id int not null auto_increment,
    primary key(id),
    user_id int,
    foreign key(user_id) references UserAccount(id),
    item_id int,
    price double,
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

INSERT INTO role (`id`, `role_name`) VALUES ('1', 'user');
INSERT INTO role (`id`, `role_name`) VALUES ('2', 'admin');

INSERT INTO UserAccount (username, password, game_account_id, role_id) values ('Salt','123456','1','2');
INSERT INTO UserAccount (username, password, game_account_id, role_id) values ('LongJin','123456','2','2');
INSERT INTO UserAccount (username, password, game_account_id, role_id) values ('LunaSimp','123456','3','2');
INSERT INTO UserAccount (username, password, game_account_id, role_id) values ('Hiep1210','123456','4','2');

INSERT INTO notification (`date`, `user_id`, `noti_content`, `content_type`) VALUES ('2023-05-01', '1', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 'admin');
INSERT INTO notification (`date`, `user_id`, `noti_content`, `content_type`) VALUES ('2023-05-01', '1', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 'admin');
INSERT INTO notification (`date`, `user_id`, `noti_content`, `content_type`) VALUES ('2023-05-01', '3', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 'admin');
INSERT INTO notification (`date`, `user_id`, `noti_content`, `content_type`) VALUES ('2023-05-01', '4', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 'admin');
INSERT INTO notification (`date`, `user_id`, `noti_content`, `content_type`) VALUES ('2023-05-01', '2', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 'buy');
INSERT INTO notification (`date`, `user_id`, `noti_content`, `content_type`) VALUES ('2023-05-01', '3', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 'buy');



INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Victoria','CZ75-Auto','Pistol','Covert','CZ75-Auto_Victoria');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Code Red','Desert Eagle','Pistol','Covert','Desert Eagle_Code Red');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Mecha Industries','Desert Eagle','Pistol','Classified','Desert Eagle_Mecha Industries');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Cobra Strike','Dual Berettas','Pistol','Classified','Dual Berettas_Cobra Strike');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Nightshade','Five-SeveN','Pistol','Mil-spec','Five-SeveN_Nightshade');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Red Tire','Glock-18','Pistol','Industrial','Glock-18_Red Tire');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Acid Etched','P2000','Pistol','Restricted','P2000_Acid Etched');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Apep Curse','P250','Pistol','Classified','P250_Apep Curse');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Bone Mask','R8 Revolver','Pistol','Consumer','R8 Revolver_Bone Mask');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Banana Cannon','R8 Revolver','Pistol','Restricted','R8 Revolver_Banana Cannon');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Fire Serpent','AK-47','Rifle','Covert','AK-47_Fire Serpant');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Jet Set','AK-47','Rifle','Classified','AK-47_Jet Set');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Radiation Hazard','AUG','Rifle','Industrial','AUG_Radiation Hazard');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Fade','AWP','Rifle','Covert','AWP_Fade');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Safari Mesh','G3SG1','Rifle','Consumer','G3SG1_Safari Mesh');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Eco','Galil AR','Rifle','Classified','Galil AR_Eco');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Hyper Beast','M4A1-S','Rifle','Covert','M4A1-S_Hyper Beast');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Basilisk','M4A1-S','Rifle','Restricted','M4A1-S_Basilisk');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Neo-Noir','M4A4','Rifle','Covert','M4A4_Neo-Noir');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Jungle Slipstream','SCAR-20','Rifle','Mil-spec','SCAR-20_Jungle Slipstream');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Cyberforce','SG 553','Rifle','Mil-spec','SG 553_Cyberforce');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Slashed','SSG 08','Rifle','Mil-spec','SSG 08_Slashed');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Neon Rider','MAC-10','SMGs','Covert','MAC-10_Neon Rider');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Sakkaku','MAC-10','SMGs','Restricted','MAC-10_Sakkaku');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Dirt Drop','MP5-SD','SMGs','Consumer','MP5-SD_Dirt Drop');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Abyssal Apparition','MP7','SMGs','Classified','MP7_Abyssal Apparition');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Hot Rod','MP9','SMGs','Mil-spec','MP9_Hot Rod');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Judgement of Anubis','PP-Bizon','SMGs','Covert','PP-Bizon_Judgement of Anubis');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Blind Spot','P90','SMGs','Restricted','P90_Blind Spot');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Asiimov','P90','SMGs','Covert','P90_Asiimov');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Chainmail','MAG-7','Heavy','Industrial','MAG-7_Chainmail');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Hyper Beast','Nova','Heavy','Classified','Nova_Hyper Beast');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Clear Polymer','Nova','Heavy','Industrial','Nova_Clear Polymer');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('The Kraken','Sawed-Off','Heavy','Covert','Sawed-Off_The Kraken');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Banana Leaf','XM1014','Heavy','Industrial','XM1014_Banana Leaf');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('System Lock','M249','Heavy','Mil-spec','M249_System Lock');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Power Loader','Negev','Heavy','Restricted','Negev_Power Loader');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Autotronic','Bayonet','Knife','Covert','Bayonet_Autotronic');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Blue Steel','Shadow Daggers','Knife','Covert','Shadow Daggers_Blue Steel');
INSERT INTO gameItems(skin_name, item_name, type, rarity, img) values ('Fade','Survival Knife','Knife','Covert','Survival Knife_Fade');

INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '1');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '2');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '3');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '4');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '5');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '6');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '7');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '8');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '9');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '10');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '11');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '12');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '13');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '14');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '15');
INSERT INTO `game_items_trading`.`marketitems` (`user_id`, `item_id`) VALUES ('1', '16');
