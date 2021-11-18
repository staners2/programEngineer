#!/bin/bash

source_dir="./src/main/kotlin"

out_dir=./out

if [ ! -f $out_dir ]
    then mkdir $out_dir
fi

for (( i=1; i <= 6; i++ ))
do
    cmd="kotlinc $source_dir/task$i.kt -include-runtime -d $out_dir/task$i.jar"
    $cmd
done