use Design
go

create table tblexams (
    exam_id int primary key,
    exam_title varchar(100) not null,
    subject varchar(50) not null,
    category_id int not null,
    total_marks int not null check (total_marks > 0),
    duration int not null check (duration > 0), 
    foreign key (category_id) references tblexamcategories(category_id) on delete cascade
);
