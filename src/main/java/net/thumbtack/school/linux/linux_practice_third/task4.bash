#! /bin/bash

for file in $(find . -name "*.java")
do
	numberOfConcidiences=`grep -c ArrayList $file`
	if [[ $numberOfConcidiences -gt 0 ]]; then
		cat $file >> textFilesWithConsidiences.txt
	fi
done
