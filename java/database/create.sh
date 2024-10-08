export PGPASSWORD='postgres1'

BASEDIR="C:/Users/Student/workspace/java-blue-finalcapstone-team3/java/database"
DATABASE="final_capstone"

psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql" &&

psql -U postgres -d $DATABASE -f "$BASEDIR/cron_job.sql"

