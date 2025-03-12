CREATE DATABASE prj301_WS1
GO

USE prj301_WS1
GO

CREATE TABLE tblUsers (
    Username VARCHAR(50) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('Founder', 'Team Member'))
);

INSERT INTO tblUsers (username, Name, password, role) 
VALUES 
('admin01', 'Nguyễn Hoàng Bảo Thy', 'admin123', 'Founder'),
('user01', 'Nguyễn Võ Anh Thy', 'user123', 'Team Member')

