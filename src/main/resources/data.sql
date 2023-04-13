-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.

CREATE TABLE PLAY_RESULT
(
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE LOG
(
    id      INT         NOT NULL AUTO_INCREMENT,
    game_id INT         NOT NULL,
    name    VARCHAR(50) NOT NULL,
    move    INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES PLAY_RESULT (id)
);
