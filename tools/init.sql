use planning;

insert into roles (roles_id,name) values
(1, "User"),(2, "Admin"),(3, "Super");

insert into users (user_id,first_name,last_name,email,password,pseudo,role_roles_id,activate) values
(1,"Jean","Bon","super@super.com","super","Superman",3,true);