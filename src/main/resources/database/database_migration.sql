CREATE TABLE IF NOT EXISTS "user" (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "balance" (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    balance DECIMAL(10,4) DEFAULT 0,
    date DATE NOT NULL DEFAULT current_date,
    id_user uuid NOT NULL,
    currency_code VARCHAR(3) NOT NULL,
    CONSTRAINT balance_pk PRIMARY KEY (id)
);

ALTER TABLE "balance" ADD CONSTRAINT user_fk FOREIGN KEY (id_user)
    REFERENCES "user" (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS "crypto_balance" (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    balance DECIMAL(10,8) DEFAULT 0,
    date DATE NOT NULL DEFAULT current_date,
    id_user uuid NOT NULL,
    currency_code VARCHAR NOT NULL,
    wallet_address VARCHAR,
    private_key VARCHAR,
    network VARCHAR,
    CONSTRAINT crypto_balance_pk PRIMARY KEY (id)
);

ALTER TABLE "crypto_balance" ADD CONSTRAINT user_fk FOREIGN KEY (id_user)
    REFERENCES "user" (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS "transaction" (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    id_user uuid NOT NULL,
    amount DECIMAL(10,4) NOT NULL,
    label VARCHAR,
    date DATE NOT NULL DEFAULT current_date,
    receiver VARCHAR NOT NULL,
    currency_code VARCHAR NOT NULL,
    type VARCHAR NOT NULL,
    CONSTRAINT transaction_pk PRIMARY KEY (id)
    );

ALTER TABLE "transaction" ADD CONSTRAINT user_fk FOREIGN KEY (id_user)
    REFERENCES "user" (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;