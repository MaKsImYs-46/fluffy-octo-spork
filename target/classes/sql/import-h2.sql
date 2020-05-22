insert into chat(chat_id, title) values
(1, 'Chat1'),
(2, 'Chat2');

insert into user(user_id, name, password, role) values
(1, 'admin', 'qwerty', 1),
(2, 'tony', 'qwerty', 2),
(3, 'stive', 'qwerty', 2),
(4, 'bruce', 'qwerty', 2);

insert into message(message_id, text, user_id, chat_id) values
(1, 'asdsadsadsadasdas', 2, 1),
(2, 'werrewtrtyrtyrtytr', 3, 1);

insert into user_chat(user_id, chat_id) values
(2, 1),
(3, 1),
(4, 1),
(3, 2),
(4, 2);