#!/bin/bash

cd ~/linux_ex/linux_practice_third/
mkdir foldersYear
cd foldersYear/
echo "Folder created!"
for ((i = 0; i < 366; i++)) {
	currentData=$(date -d "2020-01-01 + $i day" +%Y-%m-%d).csv
	touch $currentData
	echo "cite        ; country ; date       ; views ; clicks" >> $currentData
	echo "www.abc.com ; USA     ; ${currentData%.*} ; $(( ( RANDOM % 9999 )  + 1000 ))  ; $(( ( RANDOM % 9999 )  + 1 ))" >> $currentData
	echo "www.cba.com ; France  ; ${currentData%.*} ; $(( ( RANDOM % 9999 )  + 1 ))  ; $(( ( RANDOM % 9999 )  + 1000 ))" >> $currentData
}
