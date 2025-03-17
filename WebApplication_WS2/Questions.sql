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

INSERT INTO tblQuestions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES
(1, 'What is the output of System.out.println(2 + "2") in Java?', '22', '4', 'Error', 'Null', 'A'),
(1, 'Which keyword is used to define a class in Java?', 'function', 'define', 'class', 'void', 'C'),
(2, 'What is the worst-case time complexity of quicksort?', 'O(n log n)', 'O(n^2)', 'O(n)', 'O(1)', 'B'),
(3, 'Which SQL statement is used to remove all records from a table without deleting the table itself?', 'DROP', 'DELETE', 'TRUNCATE', 'REMOVE', 'C'),
(4, 'What does P(A|B) represent in probability?', 'Joint probability', 'Conditional probability', 'Marginal probability', 'Independent probability', 'B'),
(5, 'Which protocol is used to securely transfer web pages over the internet?', 'HTTP', 'HTTPS', 'FTP', 'SMTP', 'B');

