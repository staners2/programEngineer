fun main(args: Array<String>){
    args.groupingBy { it }.eachCount().toSortedMap().toList().sortedByDescending{ it.second }.toMap().forEach(){
        println("${it.key} ${it.value}")
    }
}