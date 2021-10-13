fun main(args: Array<String>){
    args.groupingBy { it }.eachCount().toSortedMap().toList().sortedByDescending { (key, value) -> value }.toMap().forEach(){
        println("${it.key} ${it.value}")
    }
}