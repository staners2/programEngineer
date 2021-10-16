#!/bin/bash

declare -a expected

params="the quick brown fox jumps over the lazy dog"

expected[1]="the\nquick\nbrown\nfox\njumps\nover\nthe\nlazy\ndog"
expected[2]="brown\ndog\nfox\njumps\nlazy\nover\nquick\nthe\nthe"
expected[3]="brown\ndog\nfox\njumps\nlazy\nover\nquick\nthe"
expected[4]="brown 1\ndog 1\nfox 1\njumps 1\nlazy 1\nover 1\nquick 1\nthe 2"
expected[5]="the 2\nbrown 1\ndog 1\nfox 1\njumps 1\nlazy 1\nover 1\nquick 1"
expected[6]="the 2\nbrown 1\ndog 1\nfox 1\njumps 1\nlazy 1\nover 1\nquick 1"

for (( i=1; i <= 6; i++ ))
do
    printf "task$i test running..."
    
    out=$(java -jar ./out/task$i.jar $params)

    printf "\nExpected result: \n${expected[$i]}"
    printf "\n\nActual result: \n$out"

    printf "\n--------------------------------------------\n"

done