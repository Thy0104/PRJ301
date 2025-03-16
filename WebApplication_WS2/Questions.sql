use Design;
go

create table tblquestions (
    question_id int primary key identity(1,1),
    exam_id int not null,
    question_text text not null,
    option_a varchar(100) not null,
    option_b varchar(100) not null,
    option_c varchar(100) not null,
    option_d varchar(100) not null,
    correct_option char(1) not null check (correct_option in ('A', 'B', 'C', 'D')),
    foreign key (exam_id) references tblexams(exam_id) on delete cascade
);
