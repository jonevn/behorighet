create table anvandare (
    id BINARY(16) not null, 
    anvandarnamn varchar(255) not null, 
    efternamn varchar(255) not null, 
    epost varchar(255) not null, 
    fornamn varchar(255) not null, 
    primary key (id));
create table anvandare_roll (anvandare_id BINARY(16) not null, roll_id BINARY(16) not null);
create table rattighet (id BINARY(16) not null, beskrivning varchar(255), namn varchar(255) not null, primary key (id));
create table roll (id BINARY(16) not null, beskrivning varchar(255), namn varchar(255) not null, primary key (id));
create table roll_rattighet (roll_id BINARY(16) not null, rattighet_id BINARY(16) not null);