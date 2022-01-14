DROP TABLE IF EXISTS "portfolio";
DROP TABLE IF EXISTS "transaction";
DROP TABLE IF EXISTS "share";
DROP TABLE IF EXISTS "user";
CREATE TABLE "share"(
    "symbol" CHAR(3) PRIMARY KEY CHECK(UPPER("symbol") = "symbol"),
    "price" DECIMAL(12, 2) NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
CREATE TABLE "user"("id" SERIAL PRIMARY KEY);
CREATE TABLE "transaction"(
    "id" SERIAL PRIMARY KEY,
    "user_id" INTEGER NOT NULL REFERENCES "user"("id"),
    "type" VARCHAR(4) NOT NULL,
    "price" DECIMAL(12, 2) NOT NULL,
    "share_symbol" VARCHAR(3) NOT NULL CHECK(UPPER("share_symbol") = "share_symbol"),
    "quantity" INTEGER CHECK ("quantity" > 0) NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
CREATE TABLE "portfolio"(
    "id" SERIAL PRIMARY KEY,
    "user_id" INTEGER NOT NULL REFERENCES "user"("id"),
    "share_symbol" VARCHAR(3) NOT NULL REFERENCES "share"("symbol") CHECK(UPPER("share_symbol") = "share_symbol"),
    "quantity" INTEGER CHECK ("quantity" > 0) NOT NULL,
    UNIQUE("share_symbol", "user_id")
);