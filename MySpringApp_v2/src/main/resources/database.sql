INSERT INTO users (id, email, firstname, lastname, password, username, enabled, confirmed)
VALUES
(1, 'creator@google.com', 'Conference', 'Creator',
'$2a$10$skVK2X2A2Jg3eYeF.nOc9./2vMWNfWQBzw0TF0gLAtYAJAxAad3ba', 'creator@google.com', TRUE, FALSE),
(2, 'nikolaevdmv@gmail.com', 'Plain', 'User',
'$2a$10$skVK2X2A2Jg3eYeF.nOc9./2vMWNfWQBzw0TF0gLAtYAJAxAad3ba', 'nikolaevdmv@gmail.com', TRUE, FALSE),
(3, 'first_reviewer@google.com', 'First', 'Reviewer',
'$2a$10$skVK2X2A2Jg3eYeF.nOc9./2vMWNfWQBzw0TF0gLAtYAJAxAad3ba', 'first_reviewer@google.com', TRUE, FALSE),
(4, 'second_reviewer@google.com', 'Second', 'Reviewer',
'$2a$10$skVK2X2A2Jg3eYeF.nOc9./2vMWNfWQBzw0TF0gLAtYAJAxAad3ba', 'second_reviewer@google.com', TRUE, FALSE),
(5, 'test@google.com', 'Plain', 'User',
'$2a$10$skVK2X2A2Jg3eYeF.nOc9./2vMWNfWQBzw0TF0gLAtYAJAxAad3ba', 'test@google.com', TRUE, FALSE);
ALTER sequence users_id_seq restart with 6;

INSERT INTO conference_request_status (id, name)
VALUES
(1, 'ACCEPTED'),
(2, 'DECLINED'),
(3, 'PENDING');

INSERT INTO submission_status (id, name)
VALUES
(1, 'ACCEPT'),
(2, 'REJECT'),
(3, 'PENDING');

INSERT INTO review_status(id, name)
VALUES
(1, 'REJECT'),
(2, 'PROBABLY_REJECT'),
(3, 'NO_DECISION'),
(4, 'PROBABLY_ACCEPT'),
(5, 'ACCEPT');

INSERT INTO conference (id, acronym, city, country, title, webpage, expirationDate)
VALUES
(1, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-06-20'),
(2, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(3, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(4, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(5, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(6, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(7, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(8, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(9, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru', '2018-05-16'),
(10, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru','2018-05-16'),
(11, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru','2018-05-16'),
(12, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru','2018-05-16'),
(13, 'MyConf', 'Saratov', 'Russia', 'My conference', 'www.my-conf.ucoz.ru','2018-05-16');
ALTER sequence conference_id_seq restart with 14;


INSERT INTO submission (id, reviewable, title, conference_id, status_id, author_id)
VALUES (1, FALSE, 'Test file', 1, 3, 1);
ALTER sequence submission_id_seq restart with 2;

INSERT INTO authority (id, name)
VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO role_list (id)
VALUES (1), (2), (3),(4), (5);
ALTER sequence role_list_id_seq restart with 6;

INSERT INTO conference_role (id, name)
VALUES
(1, 'SUBMITTER'),
(2, 'REVIEWER'),
(3, 'CREATOR'),
(4, 'ADMIN');

INSERT INTO submission_role (id, name)
VALUES
(1, 'AUTHOR'),
(2, 'REVIEWER');

INSERT INTO role_list_roles (role_list_id, role_id)
VALUES
(1, 3),
(2, 1),
(3, 2),
(4, 2),
(5, 1);

INSERT INTO conference_user_roles (id, conference_id, role_list_id, user_id)
VALUES
-- 1 - creator
(1, 1, 1, 1),
-- 3 - first author
(2, 1, 3, 3),
-- 4 - second author
(3, 1, 4, 4),
-- 2 - participant
(4, 1, 2, 2),

(5,1, 5, 5);
ALTER sequence conference_user_roles_id_seq restart with 6;


INSERT INTO submission_user_roles (id, role_id, submission_id, user_id)
VALUES (1, 1, 1, 1);
ALTER sequence submission_user_roles_id_seq restart with 2;

INSERT INTO user_authority (user_id, authority_id)
VALUES
(1, 1),
(1, 2),
(3, 1),
(4, 1);