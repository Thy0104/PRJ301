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

INSERT INTO tblUsers (username, name, password, role) VALUES
('nguyenvanphuc', 'Nguyễn Văn Phúc', 'phuc123', 'Instructor'),
('tranthithuy', 'Trần Thị Thúy', 'thuysecure', 'Student'),
('leminhquan', 'Lê Minh Quân', 'quanpass', 'Instructor'),
('phamngocanh', 'Phạm Ngọc Ánh', 'anh987', 'Student'),
('hoangthanhdat', 'Hoàng Thành Đạt', 'datpass', 'Instructor'),
('dangthikieu', 'Đặng Thị Kiều', 'kieupass', 'Student');
