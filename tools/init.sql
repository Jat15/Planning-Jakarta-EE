use planning;

insert into roles (role_id,name) values
(1, "User"),(2, "Admin"),(3, "Super");

insert into users (first_name,last_name,email,password,pseudo,role_id,activate) values
    ("Jean","Bon","super@super.com","super","Superman",3,true),
    ("Sonny","Lerasle","sonny@super.com","1234","Batman",1,true),
    ("Thibaud","Bazin","thibaud@hotmail.com","azerty","Pie",1,true),
    ("Larbi","Lachgare","larbi@gmail.com","test","Zero",1,true),
    ("Boris","Sauvage","boris@super.com","67YHG","inconnu",1,false),
    ("Yassine","haouam","yassine@super.com","1234","test",1,true),
    ("Hamid","Zouba","hamid@super.com","kcdk","Batman",1,true),
    ("youssef","Lachgare","youssef@super.com","jddd","junior",1,true),
    ("Maxence","tutois","maxence@super.com","jjjjj","lucky",1,true),
    ("Pierre","Buirette","pierre@super.com","azerty","star",1,true);