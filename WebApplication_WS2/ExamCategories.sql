use Design
go

create table tblExamCategories(
	category_id int primary key,
	category_name varchar(50) not null check (category_name in ('Quiz','Midterm','Final')),
	description text null
)

INSERT INTO tblExamCategories (category_id, category_name, description) VALUES
(1, 'Quiz', 'Short assessments to test knowledge on specific topics.'),
(2, 'Midterm', 'Exams conducted in the middle of the course to evaluate progress.'),
(3, 'Final', 'Comprehensive exams at the end of the course covering all topics.');
