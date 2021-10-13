fun main(args: Array<String>){
    val list : List<String>? = if (args.isNullOrEmpty()) readLine()?.split(" ")?.toList() else args.toList()
    list.groupingBy { it }.eachCount().toSortedMap().toList().sortedByDescending { (key, value) -> value }.toMap().forEach(){
        println("${it.key} ${it.value}")
    }
}