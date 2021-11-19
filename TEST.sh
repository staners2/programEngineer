#!/bin/bash

declare -a input

# Логин и пароль
input[0]="-login admin -password 00000"
input[1]="-password 00000 -login admin"
input[2]="-login \"?login?\" -password 00000"
input[3]="-login NotRegUser -password NotRegUser"
input[4]="-login admin -password WrongPassword"

# Логин, Пароль, Ресурс
input[5]="-login admin -password 00000 -role READ -resource A"
input[6]="-login admin -password 00000 -role WRITE -resource \"A.B\""
input[7]="-login admin -password 00000 -role UNKNOWNROLE -resource A"
input[8]="-login admin -password 00000 -role EXECUDE -resource A"

# Логин, Пароль, Ресурс, Дата начала, Дата завершения, Объем
input[9]="-login admin -password 00000 -role READ -resource A -ds \"2015-12-31\" -de \"2016-12-31\" -vol 55"
input[10]="-login admin -password 00000 -role READ -resource A -ds \"2017-12-31\" -de \"2016-12-31\" -vol 55"
input[11]="-login admin -password 00000 -role READ -resource A -ds \"time_start\" -de \"time_end\" -vol 55"
input[12]="-login admin -password 00000 -role READ -resource A -ds \"2017-12-31\" -de \"2016-12-31\" -vol NOT_VALID"

# Справка
input[13]="-login admin -h"
input[14]=""

expectedCodes=(0 0 2 3 4 0 0 5 6 0 7 7 7 0 0)

count=0

for ((i = 0; i < 15; i++)); do
  printf "test %s running...\n" "$i"

  java ./out/main.jar application.main.MainKt ${input[$i]}

  code=$?

  echo $code
  echo "${expectedCodes[$i]}"

  if [[ "$code" == "${expectedCodes[$i]}" ]]; then
    printf "\ntest %d passed\n" "$i"
  else
    printf "\ntest %d failed\n" "$i"
    ((count++))
  fi
done

if [[ $count -gt 0 ]]; then
  printf "%s" "$count"
  printf "\nError tests running\n"
  exit 1
else
  printf "Tests succeed\n"
  exit 0
fi