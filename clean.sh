#!/usr/bin/env bash

set -e

find app/org/omg/sysml/metamodel/ -type f -not -name 'SysMLType.java' -not -name 'SysMLTypeImpl.java' -delete
rm generated/org/omg/sysml/metamodel/impl/*
rm conf/json/schema/metamodel/*
rm public/jsonld/metamodel/*