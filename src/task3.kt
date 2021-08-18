fun main(args: Array<String>){
    println("Task 3 (слова должны быть уникальными)")
    if (args.isNullOrEmpty()){
        println("Ошибка: Передайте параметры через пробел, при запуске файла")
        return
    }
    args.toSet().forEach(){
        println(it)
    }
}