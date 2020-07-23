CREATE TABLE IF NOT EXISTS word
(
    id          INTEGER PRIMARY KEY,
    name        TEXT NOT NULL,
    ruby        TEXT NOT NULL,
    description TEXT NOT NULL,
    dictionary  TEXT NOT NULL
);
