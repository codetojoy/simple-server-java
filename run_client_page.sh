#!/bin/bash

curl -X GET "http://localhost:8080/account/page?page_num=0&page_size=20&meta_max_size=50" | jq > page0.json

curl -X GET "http://localhost:8080/account/page?page_num=1&page_size=20&meta_max_size=50" | jq > page1.json

curl -X GET "http://localhost:8080/account/page?page_num=2&page_size=20&meta_max_size=50" | jq > page2.json

