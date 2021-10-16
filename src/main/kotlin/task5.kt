fun main(args: Array<String>){
    args.groupingBy { it }.eachCount().toSortedMap().toList().sortedByDescending{ it.second }.toMap().map { "${it.key} ${it.value}" }.forEach(::println)
}