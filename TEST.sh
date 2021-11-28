#!/bin/bash

declare -a input

# Логин и пароль
input[0]="0-login admin -password 00000"
input[1]="0-password 00000 -login admin"
input[2]="2-login ?login? -password 00000"
input[3]="3-login NotRegUser -password NotRegUser"
input[4]="4-login admin -password WrongPassword"

# Логин, Пароль, Ресурс
input[5]="0-login admin -password 00000 -role READ -resource A"
input[6]="0-login admin -password 00000 -role WRITE -resource A.B"
input[7]="5-login admin -password 00000 -role UNKNOWNROLE -resource A"
input[8]="6-login admin -password 00000 -role EXECUTE -resource A"

# Логин, Пароль, Ресурс, Дата начала, Дата завершения, Объем
input[9]="0-login admin -password 00000 -role READ -resource A -ds 2015-12-31 -de 2016-12-31 -vol 55"
input[10]="7-login admin -password 00000 -role READ -resource A -ds 2017-12-31 -de 2016-12-31 -vol 55"
input[11]="7-login admin -password 00000 -role READ -resource A -ds time_start -de time_end -vol 55"
input[12]="7-login admin -password 00000 -role READ -resource A -ds 2015-12-31 -de 2016-12-31 -vol NOT_VALID"

# Справка
input[13]="0-login admin -h"
input[14]="0"

count=0

for ((i = 0; i < 15; i++)); do
  printf "\ntest %s running...\n" "$i"

  java -cp ./lib/kotlinx-cli-0.2.1.jar:main.jar app.MainKt ${input[$i]:1}
  code=$?

  expectedCode="${input[$i]:0:1}"

  echo $code
  echo "${expectedCode}"

  if [[ "$code" == "${expectedCode}" ]]; then
    printf "test %d passed\n" "$i"
  else
    printf "test %d failed\n" "$i"
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