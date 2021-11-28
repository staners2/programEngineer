#!/bin/bash

params=""

while [ -n "$1" ]
do
case "$1" in
-login) params+="$1 $2" ;;
-password) params+=" $1 $2" ;;
-role) params+=" $1 $2" ;;
-resource) params+=" $1 $2" ;;
-ds) params+=" $1 $2" ;;
-de) params+=" $1 $2" ;;
-vol) params+=" $1 $2" ;;
esac
shift
done

java -cp ./lib/kotlinx-cli-0.2.1.jar:main.jar app.MainKt ${params}
code=$?

echo $code