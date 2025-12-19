CREATE TABLE projects (
                          id      BIGSERIAL PRIMARY KEY,
                          name    VARCHAR(255),
                          type    VARCHAR(255)
);

CREATE TABLE "users-list" (
                              id      BIGSERIAL PRIMARY KEY,
                              name    VARCHAR(255),
                              surname VARCHAR(255)
);

CREATE TABLE tasks (
                       id       BIGSERIAL PRIMARY KEY,
                       name     VARCHAR(255),
                       status   VARCHAR(255),
                       user_id  BIGINT,
                       CONSTRAINT fk_tasks_user
                           FOREIGN KEY (user_id)
                               REFERENCES "users-list"(id)
);

CREATE TABLE task_project (
                              task_id    BIGINT NOT NULL,
                              project_id BIGINT NOT NULL,
                              PRIMARY KEY (task_id, project_id),
                              CONSTRAINT fk_task_project_task
                                  FOREIGN KEY (task_id)
                                      REFERENCES tasks(id),
                              CONSTRAINT fk_task_project_project
                                  FOREIGN KEY (project_id)
                                      REFERENCES projects(id)
);
