#!/bin/bash
export PGPASSWORD='postgres1'
BASEDIR=$(dirname $0)
DATABASE=final_capstone
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"

yelp.api.key=p1esKqYoAZ8FskOPXp8arx29CSaHpNqw
yelp.api.url=https://api.giphy.com/v1/gifs/search?api_key=
