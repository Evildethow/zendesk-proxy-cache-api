CREATE TABLE "comments" (
  "id"      BIGINT PRIMARY KEY,
  "ticket_id" BIGINT NOT NULL,
  "body"   VARCHAR NOT NULL
);
ALTER TABLE "comments" ADD CONSTRAINT "TICKET_FK" FOREIGN KEY ("ticket_id") REFERENCES "tickets" ("id") ON UPDATE RESTRICT ON DELETE CASCADE;