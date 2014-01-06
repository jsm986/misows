1. git clone git://github.com/jsm986/misows.git
2. cd misows
3. heroku create
4. heroku plugins:install https://github.com/heroku/heroku-deploy	--> instalacja pluginu do deploymentu aplikacji
5. heroku addons:add cleardb:ignite			--> utworzenie bazy daych 
6. heroku config                            --> wypisze na wyjscie login, haslo, URL, schemat bazy
7. parametry bazy umiescic w pliku src/main/resources/spring-context.xml
8. polaczyc sie jakims klientem do bazy i stworzyc tabele: 

create table mock_session (
	id integer not null auto_increment,
	hkey varchar(3000),
	hvalue varchar(3000),
	calculations varchar(8000),
	primary key(id)
);

9. sprawdzic czy tabela zostala poprawnie utworzona
10. mvn clean install
11. heroku deploy:war --war target\grids-0.0.1-SNAPSHOT.war --app <app_name>  --> deploy zbudowanego w punkcie 10 pliku .war. trwa to chwilke
12. Sprawdzic czy aplikacja dziala (heroku ps; z panelu przegladrkowego heroku; wejsc na aplikacje <app_name>_herokuapp.com/calculate.do
13. heroku ps:scale web=x --> wlaczyc odpowiednia liczbe dyno, x == liczba dyno
14. heroku ps:restart --> restart dyno
15. Aplikacja: 
				<app_name>.herokuapp.com/calculate.do  -->  przycisk continue czysci baze przed nowymi obliczeniami
				<app_name>.herokuapp.com/calculate.do?node=x  -->  uruchamia obliczenia na nodzie x i wynik zapisuje w bazie. Po ponownym wpisaniu adresu noda, obliczenia nie sa na nowo uruchamiana tylko output z nich jest pobierany z bazy i wypisywany.

