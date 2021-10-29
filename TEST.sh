#!/bin/bash

declare -a expected

params="the quick brown fox jumps over the lazy dog"
n=$'\n'

expected[1]="the${n}quick${n}brown${n}fox${n}jumps${n}over${n}the${n}lazy${n}dog"
expected[2]="brown${n}dog${n}fox${n}jumps${n}lazy${n}over${n}quick${n}the${n}the"
expected[3]="brown${n}dog${n}fox${n}jumps${n}lazy${n}over${n}quick${n}the"
expected[4]="brown 1${n}dog 1${n}fox 1${n}jumps 1${n}lazy 1${n}over 1${n}quick 1${n}the 2"
expected[5]="the 2${n}brown 1${n}dog 1${n}fox 1${n}jumps 1${n}lazy 1${n}over 1${n}quick 1"
expected[6]="the 2${n}brown 1${n}dog 1${n}fox 1${n}jumps 1${n}lazy 1${n}over 1${n}quick 1"

successful=0
failed=1

for (( i=1; i <= 6; i++ ))
do
    echo "task$i test running..."
    
    out=$(java -jar ./out/task${i}.jar $params)

    echo "Expected result:"
    printf "${expected[${i}]}\n\n"

    echo "Actual result:"
    echo "$out"

    echo "--------------------------------------------"

    if [[ "${expected[${i}]}" == "$out" ]]; then
        (( successful += 1 ))
    else
        (( failed += 1 ))
    fi

done

if [[ $failed != 0 ]]; then
    exit 1
fi

echo "Result positive test: $successful/6"