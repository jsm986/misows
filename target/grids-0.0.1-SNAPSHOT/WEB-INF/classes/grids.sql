create table mock_session (
	id integer not null auto_increment,
	hkey varchar(3000),
	hvalue varchar(3000),
	calculations varchar(8000),
	primary key(id)
);