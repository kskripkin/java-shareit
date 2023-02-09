CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     name VARCHAR(100) NOT NULL,
                                     email VARCHAR(100) NOT NULL UNIQUE,
                                     CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS items (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     name VARCHAR(255) NOT NULL,
                                     description VARCHAR(512),
                                     available BOOLEAN NOT NULL,
                                     owner_id BIGINT NOT NULL,
                                     CONSTRAINT pk_item PRIMARY KEY (id),
                                     CONSTRAINT fk_items_to_users FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS bookings (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     start_time TIMESTAMP NOT NULL,
                                     end_time TIMESTAMP NOT NULL,
                                     item_id BIGINT NOT NULL,
                                     booker_id BIGINT NOT NULL,
                                     status VARCHAR(30) NOT NULL,
                                     item_name VARCHAR(255),
                                     CONSTRAINT pk_booking PRIMARY KEY (id),
                                     CONSTRAINT fk_bookings_to_users FOREIGN KEY(booker_id) REFERENCES users(id),
                                     CONSTRAINT fk_bookings_to_items FOREIGN KEY(item_id) REFERENCES items(id)
);

CREATE TABLE IF NOT EXISTS comments (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     text VARCHAR(255) NOT NULL,
                                     author_name VARCHAR(255) NOT NULL,
                                     item_id BIGINT,
                                     created TIMESTAMP NOT NULL,
                                     CONSTRAINT pk_comment PRIMARY KEY (id),
                                     CONSTRAINT fk_comments_to_items FOREIGN KEY(item_id) REFERENCES items(id)
);