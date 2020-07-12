#! /bin/bash

cd ./foldersYear/
rm tempfile;
mkdir weeks; cd weeks
touch Понедельник.csv Вторник.csv Среда.csv Четверг.csv Пятница.csv Суббота.csv Воскресенье.csv
cd ..
ls | while read filename;
do 
	dayOfWeek=`date -d ${filename%.*} +%A`
	ls weeks | while read file
	do
		day=${file%.*}
		if [[ $day == $dayOfWeek ]]; then
			cat $filename >> weeks/$file
		fi	
	done
done
cd weeks
ls | while read days;
do
	cat $days | sort -u -k2 > tempfile
	mv tempfile $days
done	
