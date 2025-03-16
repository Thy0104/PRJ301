use Design
go

create table tblExamCategories(
	category_id int primary key,
	category_name varchar(50) not null check (category_name in ('Quiz','Midterm','Final')),
	description text null
)
