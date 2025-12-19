CREATE TABLE t_permission (
                              id   BIGSERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE t_users (
                         id       BIGSERIAL PRIMARY KEY,
                         email    VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE t_user_permission (
                                   user_id       BIGINT NOT NULL,
                                   permission_id BIGINT NOT NULL,
                                   CONSTRAINT fk_user_permission_user
                                       FOREIGN KEY (user_id) REFERENCES t_users(id),
                                   CONSTRAINT fk_user_permission_permission
                                       FOREIGN KEY (permission_id) REFERENCES t_permission(id),
                                   CONSTRAINT pk_user_permission PRIMARY KEY (user_id, permission_id)
);

INSERT INTO t_permission (name) VALUES ('ROLE_USER');
