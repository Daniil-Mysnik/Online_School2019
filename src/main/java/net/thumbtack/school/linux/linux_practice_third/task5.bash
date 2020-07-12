#! /bin/bash

find /etc
for file in $(find /etc)
do
	echo ${file/'/'/'C:/'} | tr '/' '\' >> FilesETC
done

