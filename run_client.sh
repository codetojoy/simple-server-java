#!/bin/bash

curl -X GET "http://localhost:8080/simple/account?id=10&name=mozart&address=18_Longworth_Ave" | jq

