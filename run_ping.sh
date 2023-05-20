#!/bin/bash

set -e 

curl -X GET "http://localhost:8080/simple/ping" | jq
