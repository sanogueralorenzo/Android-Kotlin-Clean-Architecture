#!/bin/bash
 # Script used for CircleCI cache key.
 # Creates a checksum for all build.gradle files in your project.
 # Thank you Chris Banes: https://medium.com/@chrisbanes/circleci-cache-key-over-many-files-c9e07f4d471a
RESULT_FILE=$1
 if [ -f $RESULT_FILE ]; then
  rm $RESULT_FILE
fi
touch $RESULT_FILE
 checksum_file() {
  echo `openssl md5 $1 | awk '{print $2}'`
}
 FILES=()
while read -r -d ''; do
	FILES+=("$REPLY")
done < <(find . -name 'build.gradle' -type f -print0)
 # Loop through files and append MD5 to result file
for FILE in ${FILES[@]}; do
	echo `checksum_file $FILE` >> $RESULT_FILE
done
# Now sort the file so that it is
sort $RESULT_FILE -o $RESULT_FILE