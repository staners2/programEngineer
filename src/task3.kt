fun main(args: Array<String>){
    println("Task 3 (слова должны быть уникальными)")
    if (args.isNullOrEmpty()){
        println("Передайте параметры через пробел")
        return
    }
    args.toSet().forEach(){
        println(it)
    }
}