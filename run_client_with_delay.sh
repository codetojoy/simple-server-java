#!/bin/bash

curl -X GET "http://localhost:8080/simple/account?id=9&name=beethoven&address=Vienna&delay_in_seconds=3" | jq

