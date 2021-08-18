fun main(args : Array<String>){
    println("Task 1 (выдать список слов, разделенных пробельными символами)")
    if (args.isNullOrEmpty()){
        println("Передайте параметры через пробел")
        return
    }
    args.forEach(){
        println(it)
    }
}
