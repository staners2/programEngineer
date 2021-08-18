fun main(args: Array<String>){
    println("Task 4 (после каждого слова выводить кол-во повторений)")
    if (args.isNullOrEmpty()){
        println("Передайте параметры через пробел")
        return
    }
    args.groupingBy { it }.eachCount().toSortedMap().forEach(){
        println("${it.key} ${it.value}")
    }
}