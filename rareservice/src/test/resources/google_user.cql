CREATE KEYSPACE "rare_server" WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

CREATE TABLE "rare_server"."google_user" (
	google_id text,
	friend_google_id text,
	gender text static,
	url text static,
	total_friends int static,
	friend_google_url text,
	friend_name text,
	name text static,
	PRIMARY KEY ((google_id), friend_google_id)
 );