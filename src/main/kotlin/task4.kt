fun main(args: Array<String>){
    args.groupingBy { it }.eachCount().map {"${it.key} ${it.value}"}.forEach(::println)
}