use planning;

insert into roles (role_id,name) values
    (1, "User"),(2, "Admin"),(3, "Super");


insert into users (pseudo,first_name,last_name,email,password,birthdate,street,city,country,zip,phone,role_id,activate) values
    ("Supreme","Zeus","Dzeus","super@super.com","super","2000-12-20","01 Mont Olympes","grece","Grèce","00000","0600118218",3,true),
    ("Batman","Sonny","Lerasle","sonny@gmail.com","test","1985-11-15","17 rue dujardin","Amiens","France","80000","0600118218",1,true),
    ("Pie","Thibaud","Bazin","thibaud@gmail.com","password","1995-05-12","20 rue la hotoie","Amiens","France","80000","0600118218",1,true),
    ("Zero","Larbi","Lachgare","larbi@gmail.com","1234","1988-05-20","17 rue de paris","Amiens","France","80000","0600118218",1,true),
    ("Lucky","Maxence","Tutois","maxence@gmail.com","azerty","1989-09-27","175 rue otages","Amiens","France","80000","0600118218",1,true),
    ("Formateur","Boris","Sauvage","boris@gmail.com","motdepasse","1988-05-20","65 rue jules verne","Amiens","France","80000","0600118218",1,true),
    ("Pseudo","Yassine","Haouam","yassine@gmail.com","test1","1986-05-22","124 rue luzarches","Arras","France","62000","0600118218",1,true),
    ("TestPseudo","Hamid","Zouba","hamid@gmail.com","testpass","1990-06-20","02 rue charles dubois","Arras","France","62000","0600118218",1,true),
    ("Test","Pierre","Buirette","pierre@gmail.com","test","1992-05-11","17 rue test","Arras","France","62000","0600118218",1,true);
