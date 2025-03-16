create database Design
go

use Design
go

create table tblUsers (
	username varchar(50) primary key,
	name varchar(50) not null,
	password varchar(50) not null,
	role varchar(20) not null check (role in ('Instructor','Student'))
)