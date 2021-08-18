fun main(args: Array<String>){
    println("Task 2 (отсортировать по алфавиту в возрастающем порядке)")
    if (args.isNullOrEmpty()){
        println("Ошибка: Передайте параметры через пробел, при запуске файла")
        return
    }
    args.sorted().forEach(){
        println(it)
    }
}