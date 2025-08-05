CREATE TABLE topics (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    author VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL
);

