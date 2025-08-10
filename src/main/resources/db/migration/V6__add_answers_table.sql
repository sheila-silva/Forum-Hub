CREATE TABLE answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    topic_id INT NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topics(id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);