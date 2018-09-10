// CQL script for generating Cassandra tables

CREATE KEYSPACE IF NOT EXISTS sysml2 WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE IF NOT EXISTS sysml2.elements (id timeuuid, name text, PRIMARY KEY (id));

// Insert some test data
INSERT INTO sysml2.elements(id,name) VALUES (b774a350-f17d-11e7-ba2d-c33034458df0,'E1');
INSERT INTO sysml2.elements(id,name) VALUES (ac66bede-98f9-11e8-9eb6-529269fb1459,'E2');
INSERT INTO sysml2.elements(id,name) VALUES (b9ac03a6-98f9-11e8-9eb6-529269fb1459,'E3');
INSERT INTO sysml2.elements(id,name) VALUES (c76cc43a-98f9-11e8-9eb6-529269fb1459,'E4');
INSERT INTO sysml2.elements(id,name) VALUES (cf114aa8-98f9-11e8-9eb6-529269fb1459,'E5');

// Select queries
select * from sysml2.elements;

//select * from syndeia.repositories;
select * from syndeia.artifacts;


