-- Add 3 musical artists
INSERT INTO artists (name)
    VALUES ('Tupper Ware Remix Party'), ('Steely Dan'), ('Missy Elliott');

-- Switch to the artist role (so we're not querying from a superuser role, which
-- bypasses RLS)
SET ROLE artist;

SELECT * FROM artists;
--  artist_id |          name
-- -----------+-------------------------
--          1 | Tupper Ware Remix Party
--          2 | Steely Dan
--          3 | Missy Elliott
-- (3 rows)
