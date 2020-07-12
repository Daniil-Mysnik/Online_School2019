#! /bin/bash

cd ./foldersYear/
((numberOfFiles=`ls -l | wc -l` - 1))
if (($numberOfFiles == 366 ))
then
	echo "Number of files is correct"
	ls | while read filename;
	do
		cat $filename | awk -F "; " '{print $3 "; " $1 "; " $2 "; " $4 "; " $5}' >  tmpfile
	mv tmpfile $filename
	done;
else
	echo "Number of files is NOT CORRECT"	
	exit
fi
