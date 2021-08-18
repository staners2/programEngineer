# FAQ #
## Необходимые инструменты ##
* Установелен JDK 8
* Установлен компилятор kotlin (kotlinc)

## Как запустить? ##
1. Скомпилировать файл из папки <b>src</b><br>
<code>kotlinc task1.kt -include-runtime -d task1.jar</code>
2. Запустить скомпилированный файл <i>(либо взять готовый файл из папки <b>jar</b>)</i><br>
<code>java -jar task1.jar arg1 arg2 {и т.д.}</code>

## Тестовые сценарии ##
### Задача 1 ###
#### Выдает список слов, разделенных пробельными символами ####
> Запуск:<br> 
>> <code>java -jar task1.jar the quick brown fox jumps over the lazy dog</code>
>
> Ответ:<br>
>> <code>the<br>
quick<br>
brown<br>
fox<br>
jumps<br>
over<br>
the<br>
lazy<br>
dog</code> 

### Задача 2 ###
#### Отсортировать по алфавиту в возрастающем порядке ####
> Запуск:<br> 
>> <code>java -jar task2.jar the quick brown fox jumps over the lazy dog</code>
>
> Ответ:<br>
>> <code>brown<br>
dog<br>
fox<br>
jumps<br>
lazy<br>
over<br>
quick<br>
the<br>
the</code> 

### Задача 3 ###
#### Слова должны быть уникальными ####
> Запуск:<br> 
>> <code>java -jar task3.jar the quick brown fox jumps over the lazy dog</code>
>
> Ответ:<br>
>> <code>the<br>
quick<br>
brown<br>
fox<br>
jumps<br>
over<br>
lazy<br>
dog</code> 

### Задача 4 ###
#### Выводить кол-во повторений слов ####
> Запуск:<br> 
>> <code>java -jar task1.jar the quick brown fox jumps over the lazy dog</code>
>
> Ответ:<br>
>> <code>brown 1<br>
dog 1<br>
fox 1<br>
jumps 1<br>
lazy 1<br>
over 1<br>
quick 1<br>
the 2</code> 

### Задача 5 ###
#### Отсортирован по кол-ву повторений в убывающем порядке, в случае одинакового кол-ва - по алфавиту ####
> Запуск:<br> 
>> <code>java -jar task5.jar the quick brown fox jumps over the lazy dog</code>
>
> Ответ:<br>
>> <code>the 2<br>
brown 1<br>
dog 1<br>
fox 1<br>
jumps 1<br>
lazy 1<br>
over 1<br>
quick 1</code> 

### Задача 6 ### 
#### Если не передать параметры, то считывать слова из стандартного потока ввода ####
> Запуск:<br>  
>> <code>echo "the quick brown fox jumps over the lazy dog" | java -jar task6.jar</code>
>
> Ответ:<br>
>> <code>brown 1<br>
dog 1<br>
fox 1<br>
jumps 1<br>
lazy 1<br>
over 1<br>
quick 1</code>

