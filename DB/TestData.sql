use game_items_trading;
INSERT INTO gameAccount( username, password, dob, email, gender, avatar) values ('Salt','123456','2003-05-01','hungdeptrai@gmail.com','Male','img');
INSERT INTO gameAccount( username, password, dob, email, gender, avatar) values ('LongJin','123456','2003-11-23','luongvippro@gmail.com','Male','img');
INSERT INTO gameAccount( username, password, dob, email, gender, avatar) values ('LunaSimp','123456','2003-12-13','nguyenchitrung@gmail.com','Male','img');
INSERT INTO gameAccount( username, password, dob, email, gender, avatar) values ('Hiep1210','123456','2003-10-12','Hoanghiepp@gmail.com','Male','img');

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


