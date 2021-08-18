fun main(args: Array<String>){
    println("Task 2 (отсортировать по алфавиту в возрастающем порядке)")
    if (args.isNullOrEmpty()){
        println("Передайте параметры через пробел")
        return
    }
    args.sorted().forEach(){
        println(it)
    }
}