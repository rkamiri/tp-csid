
Create Table repository (name varchar(50) PRIMARY KEY NOT NULL, owner varchar(50), open_issues int default 0, forks int default 0 , lastedit varchar(32));
Create Table statistiques (id serial primary key not null, name varchar(50), entry_type varchar(32), datetime varchar(32), value int);

