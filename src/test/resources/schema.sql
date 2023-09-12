CREATE TABLE markers(
  ID       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  LAT         DOUBLE NOT NULL DEFAULT 0,
  LNG         DOUBLE NOT NULL DEFAULT 0,
  DESCRIPTION VARCHAR(256) NOT NULL,
  OWNBY     VARCHAR(256) NOT NULL
);

