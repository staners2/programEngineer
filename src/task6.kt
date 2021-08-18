fun main(args: Array<String>){
    println("Task 6 (Если не передать аргументы, то считывать слова с потока ввода)")
    val list : List<String>? = if (args.isNullOrEmpty()) readLine()?.split(" ")?.toList() else args.toList()
    if (list.isNullOrEmpty()){
        println("Передайте параметры через пробел")
        return
    }
    list.groupingBy { it }.eachCount().toSortedMap().toList().sortedByDescending { (key, value) -> value }.toMap().forEach(){
        println("${it.key} ${it.value}")
    }
}