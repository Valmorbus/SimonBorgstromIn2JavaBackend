create table todo(
id int not null primary key  GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1), 
description varchar (256) not null,
duedate date not null,
done boolean
);