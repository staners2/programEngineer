fun main(args: Array<String>){
    args.sorted().groupingBy { it }.eachCount().map {"${it.key} ${it.value}"}.forEach(::println)
}