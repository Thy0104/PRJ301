USE prj301_WS1
GO

CREATE TABLE tblStartupProjects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    Description TEXT,
    Status VARCHAR(20) NOT NULL CHECK (Status IN ('Ideation', 'Development', 'Launch', 'Scaling')),
    estimated_launch DATE NOT NULL
);
GO