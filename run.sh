#!/bin/sh

docker-compose build

docker-compose up -d

set +e

docker-compose exec wlg-takehome gradle clean run --args="-file commas.txt -sort full_name"

docker-compose exec wlg-takehome gradle clean run --args="-file commas.txt -sort vehicle_type"

docker-compose exec wlg-takehome gradle clean run --args="-file pipes.txt -sort full_name"

docker-compose exec wlg-takehome gradle clean run --args="-file pipes.txt -sort vehicle_type"

docker-compose down