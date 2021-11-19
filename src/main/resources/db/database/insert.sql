insert into role(role) values('User');
insert into role(role) values('User with privileges');
insert into role(role) values('SuperUser');

insert into rule(rule) values('Read');
insert into rule(rule) values('Create, Read');
insert into rule(rule) values('Create, Read, Update, Delete');

insert into roles_rules(role_id, rule_id) values(1, 1);
insert into roles_rules(role_id, rule_id) values(2, 2);
insert into roles_rules(role_id, rule_id) values(3, 3);

insert into users(name, role_id) values('User-1', 1);
insert into users(name, role_id) values('User-2', 3);
insert into users(name, role_id) values('User-2', 2);

insert into state(state) values('State-1');
insert into state(state) values('State-2');
insert into state(state) values('State-3');

insert into category(category) values('Category-1');
insert into category(category) values('Category-2');
insert into category(category) values('Category-3');

insert into item(item, user_id, state_id, category_id) values('Item-1', 1, 1, 2);
insert into item(item, user_id, state_id, category_id) values('Item-2', 3, 1, 2);
insert into item(item, user_id, state_id, category_id) values('Item-3', 2, 2, 1);
insert into item(item, user_id, state_id, category_id) values('Item-4', 2, 3, 2);
insert into item(item, user_id, state_id, category_id) values('Item-5', 3, 2, 3);
insert into item(item, user_id, state_id, category_id) values('Item-6', 1, 3, 1);

insert into comments(comment, item_id) values('Comment-1', 1);
insert into comments(comment, item_id) values('Comment-2', 1);
insert into comments(comment, item_id) values('Comment-3', 2);
insert into comments(comment, item_id) values('Comment-4', 3);

insert into attachs(attach, item_id) values('Attach-1', 1);
insert into attachs(attach, item_id) values('Attach-2', 1);
insert into attachs(attach, item_id) values('Attach-3', 2);
insert into attachs(attach, item_id) values('Attach-4', 3);