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

INSERT INTO tblExams (exam_id, exam_title, subject, category_id, total_marks, duration) VALUES
(1, 'Basic Java Programming', 'Java', 1, 100, 60),
(2, 'Data Structures & Algorithms', 'Computer Science', 2, 100, 90),
(3, 'Relational Database Systems', 'SQL', 3, 100, 75),
(4, 'Probability & Statistics', 'Mathematics', 2, 50, 45),
(5, 'Web Development with JSP & Servlets', 'Web Development', 1, 100, 60),
(6, 'Computer Networks', 'Networking', 3, 80, 70);
