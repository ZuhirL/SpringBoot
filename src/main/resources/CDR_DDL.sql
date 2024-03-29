DROP TABLE IF EXISTS CDR;

CREATE TABLE CDR
(
    id         NUMERIC PRIMARY KEY NOT NULL,
    session_id VARCHAR(30) NOT NULL,
    vehicle_id VARCHAR(12) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time   DATETIME NOT NULL,
    total_cost DECIMAL(10, 2) NOT NULL
);

CREATE SEQUENCE CDR_SEQ START WITH 8 INCREMENT BY 1;
