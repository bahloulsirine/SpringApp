INSERT into users (username,password,enabled) values('sirine',
                                                     1111,
                                                     true);

INSERT into users (username,password,enabled) values('yasmine',
                                                     5555,
                                                     true);


INSERT into authorities (username,authority) values('sirine',
                                                     ROLE_ADMIN);

INSERT into authorities (username,authority) values('yasmine',
                                                    'ROLE_USER');