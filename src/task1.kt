fun main(args : Array<String>){
    println("Task 1 (выдать список слов, разделенных пробельными символами)")
    if (args.isNullOrEmpty()){
        println("Ошибка: Передайте параметры через пробел, при запуске файла")
        return
    }
    args.forEach(){
        println(it)
    }
}
