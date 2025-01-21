#!/usr/bin/env bash

set -e

find app/org/omg/sysml/metamodel/ -type f -not -name 'SysMLType.java' -not -name 'SysMLTypeImpl.java' -delete
rm generated/org/omg/sysml/metamodel/impl/* > /dev/null 2>&1 || true
rm conf/json/schema/metamodel/* > /dev/null 2>&1 || true
rm public/jsonld/metamodel/* > /dev/null 2>&1 || true
