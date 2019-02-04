// CQL script for generating Cassandra tables

CREATE KEYSPACE IF NOT EXISTS sysml2 WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE IF NOT EXISTS sysml2.models (identifier timeuuid, name text, description text, PRIMARY KEY (identifier));
CREATE TABLE IF NOT EXISTS sysml2.elements (identifier timeuuid, name text, description text, parent_model timeuuid, type text, PRIMARY KEY (identifier));
CREATE TABLE IF NOT EXISTS sysml2.relationships (identifier timeuuid, name text, description text, parent_model timeuuid, type text,   
		source_element_role text, source_element timeuuid, target_element_role text, target_element timeuuid, PRIMARY KEY (identifier));
