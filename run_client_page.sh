#!/bin/bash

curl -X GET "http://localhost:8080/account/page?pageNum=0&pageSize=20" | jq > page0.json

curl -X GET "http://localhost:8080/account/page?pageNum=1&pageSize=20" | jq > page1.json

curl -X GET "http://localhost:8080/account/page?pageNum=2&pageSize=20" | jq > page2.json

