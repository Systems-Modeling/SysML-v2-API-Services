// CQL script for generating Cassandra tables

CREATE KEYSPACE IF NOT EXISTS sysml2 WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

// CREATE TABLE IF NOT EXISTS sysml2.elements (identifier timeuuid, name text, PRIMARY KEY (identifier));
CREATE TABLE IF NOT EXISTS sysml2.models (identifier timeuuid, name text, description text, PRIMARY KEY (identifier));
CREATE TABLE IF NOT EXISTS sysml2.elements (identifier timeuuid, name text, description text, parent_model timeuuid, type text, PRIMARY KEY (identifier));
CREATE TABLE IF NOT EXISTS sysml2.relationships (identifier timeuuid, name text, description text, parent_model timeuuid, type text,   
		source_element_role text, source_element timeuuid, target_element_role text, target_element timeuuid, PRIMARY KEY (identifier)
);

// Insert some test data
INSERT INTO sysml2.models(identifier,name,description) VALUES (65df24d2-b52e-11e8-96f8-529269fb1459,'Sandbox model', 'This is a sandbox SysML v2 model.');

INSERT INTO sysml2.models(identifier,name,description) VALUES (c76fde60-b562-11e8-8e53-dbf8bfefb057,'MyModel','Description of My Model');

INSERT INTO sysml2.elements(identifier,name,description, parent_model, type) VALUES (65df2770-b52e-11e8-96f8-529269fb1459,'Shapes','Package of shapes',65df24d2-b52e-11e8-96f8-529269fb1459,'Package');
INSERT INTO sysml2.elements(identifier,name,description, parent_model, type) VALUES (65df28c4-b52e-11e8-96f8-529269fb1459,'Triangle','Triangle shape',65df24d2-b52e-11e8-96f8-529269fb1459,'Class');
INSERT INTO sysml2.elements(identifier,name,description, parent_model, type) VALUES (65df2a04-b52e-11e8-96f8-529269fb1459,'Quadrilateral','Quadrilateral shape',65df24d2-b52e-11e8-96f8-529269fb1459,'Class');
INSERT INTO sysml2.elements(identifier,name,description, parent_model, type) VALUES (65df2b3a-b52e-11e8-96f8-529269fb1459,'Square','Square shape',65df24d2-b52e-11e8-96f8-529269fb1459,'Class');

INSERT INTO sysml2.relationships(identifier,name,description, parent_model, type, source_element_role, source_element, target_element_role, target_element) 
VALUES (65df2c70-b52e-11e8-96f8-529269fb1459,'Shape package members','Shape package members',65df24d2-b52e-11e8-96f8-529269fb1459,'Membership','owningPackage',65df2770-b52e-11e8-96f8-529269fb1459,'memberElement',65df28c4-b52e-11e8-96f8-529269fb1459);

INSERT INTO sysml2.relationships(identifier,name,description, parent_model, type, source_element_role, source_element, target_element_role, target_element) 
VALUES (65df3062-b52e-11e8-96f8-529269fb1459,'Shape package members','Shape package members',65df24d2-b52e-11e8-96f8-529269fb1459,'Membership','owningPackage',65df2770-b52e-11e8-96f8-529269fb1459,'memberElement',65df2a04-b52e-11e8-96f8-529269fb1459);

INSERT INTO sysml2.relationships(identifier,name,description, parent_model, type, source_element_role, source_element, target_element_role, target_element) 
VALUES (65df31c0-b52e-11e8-96f8-529269fb1459,'Shape package members','Shape package members',65df24d2-b52e-11e8-96f8-529269fb1459,'Membership','owningPackage',65df2770-b52e-11e8-96f8-529269fb1459,'memberElement',65df2b3a-b52e-11e8-96f8-529269fb1459);

INSERT INTO sysml2.relationships(identifier,name,description, parent_model, type, source_element_role, source_element, target_element_role, target_element) 
VALUES (65df32ec-b52e-11e8-96f8-529269fb1459,'Shape package members','Shape package members',65df24d2-b52e-11e8-96f8-529269fb1459,'Generalization','specific',65df2b3a-b52e-11e8-96f8-529269fb1459,'general',65df2a04-b52e-11e8-96f8-529269fb1459);


// Select queries
//select * from sysml2.elements;

select * from sysml2.models;
select * from sysml2.elements;
select * from sysml2.relationships;
select * from sysml2.relationships where type = 'Generalization' allow filtering;
select * from sysml2.relationships where type = 'Membership' allow filtering;
select * from sysml2.relationships where source_element = 3921f050-b565-11e8-8e53-dbf8bfefb057 allow filtering;
select * from sysml2.relationships where target_element = 3921f050-b565-11e8-8e53-dbf8bfefb057 allow filtering;

delete from sysml2.elements where identifier=5eac6de0-b5ca-11e8-8e53-dbf8bfefb057;



