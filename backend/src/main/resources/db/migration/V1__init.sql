-- Students: every rider, driver and admin
CREATE TABLE users (
  id            BIGSERIAL PRIMARY KEY,
  name          VARCHAR(100) NOT NULL,
  dal_email     VARCHAR(120) NOT NULL UNIQUE,
  school_id     VARCHAR(20)  NOT NULL UNIQUE,
  password_hash VARCHAR(100) NOT NULL,
  verified      BOOLEAN NOT NULL DEFAULT FALSE,
  is_admin      BOOLEAN NOT NULL DEFAULT FALSE,
  active_role   VARCHAR(10) NOT NULL DEFAULT 'RIDER'
                CHECK (active_role IN ('RIDER', 'DRIVER')),
  created_at    TIMESTAMP NOT NULL DEFAULT NOW()
);

-- : drivers
CREATE TABLE drivers (
  user_id   BIGINT PRIMARY KEY REFERENCES users(id),
  car_model VARCHAR(100) NOT NULL,
  plate     VARCHAR(15)  NOT NULL UNIQUE,
  seats     INT NOT NULL CHECK (seats >= 1),
  available BOOLEAN NOT NULL DEFAULT FALSE
);
--: trips 
-- Normal one-rider trips
CREATE TABLE trips (
  id           BIGSERIAL PRIMARY KEY,
  rider_id     BIGINT NOT NULL REFERENCES users(id),
  driver_id    BIGINT REFERENCES users(id),
  pickup       VARCHAR(200) NOT NULL,
  dropoff      VARCHAR(200) NOT NULL,
  status       VARCHAR(20) NOT NULL DEFAULT 'REQUESTED'
               CHECK (status IN ('REQUESTED', 'ASSIGNED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
  fare         NUMERIC(8,2),
  requested_at TIMESTAMP NOT NULL DEFAULT NOW(),
  completed_at TIMESTAMP
);
-- Carpools a driver posts
CREATE TABLE carpool_groups (
  id              BIGSERIAL PRIMARY KEY,
  driver_id       BIGINT NOT NULL REFERENCES users(id),
  origin          VARCHAR(200) NOT NULL,
  destination     VARCHAR(200) NOT NULL,
  route           TEXT,
  departure_time  TIMESTAMP NOT NULL,
  total_seats     INT NOT NULL CHECK (total_seats > 0),
  seats_available INT NOT NULL CHECK (seats_available >= 0)
);

-- Junction table: which riders joined which carpool
CREATE TABLE carpool_members (
  carpool_id BIGINT REFERENCES carpool_groups(id),
  rider_id   BIGINT REFERENCES users(id),
  joined_at  TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (carpool_id, rider_id)
);