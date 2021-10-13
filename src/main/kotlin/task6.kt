fun main(args: Array<String>){
    val list : List<String>? = if (args.isEmpty()) readLine()?.split(" ")?.toList() else args.toList()

    if (list?.get(0) != "") {
        show(list)
    }
}

fun show(list: List<String>?){
    list?.groupingBy { it }?.eachCount()?.toSortedMap()?.toList()?.sortedByDescending { it.second }?.toMap()?.forEach(){
        println("${it.key} ${it.value}")
    }
}