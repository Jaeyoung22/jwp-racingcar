DROP TABLE IF EXISTS race_results;
DROP TABLE IF EXISTS race_cars;

CREATE TABLE IF NOT EXISTS race_results (
    id          CHAR(36)        NOT NULL,
    trial_count INT             NOT NULL,
    winners     VARCHAR(255)    NOT NULL,
    created_at  DATETIME        NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS race_cars (
    id          CHAR(36)        NOT NULL,
    name        VARCHAR(6)      NOT NULL,
    position    INT             NOT NULL,
    race_result_id CHAR(36)     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (race_result_id) REFERENCES race_results(id)
);