fun main(args: Array<String>){
    args.groupingBy { it }.eachCount().toSortedMap().forEach(){
        println("${it.key} ${it.value}")
    }
}