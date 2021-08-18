fun main(args: Array<String>){
    println("Task 5 (отсортирован сначала по количеству повторений в убывающем порядке, в случае одинакового количества – по алфавиту)")
    if (args.isNullOrEmpty()){
        println("Ошибка: Передайте параметры через пробел, при запуске файла")
        return
    }
    args.groupingBy { it }.eachCount().toSortedMap().toList().sortedByDescending { (key, value) -> value }.toMap().forEach(){
        println("${it.key} ${it.value}")
    }
}