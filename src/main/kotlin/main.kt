fun main() {
    print("숫자 개수 : ")
    var num:Int = readLine()!!.trim().toInt()
    println("${num}개의 숫자를 입력 받습니다")
    var arr: Array<Int> = Array<Int>(num,{0})
    for(i in arr.indices){
        print("${i+1}번째 숫자 : ")
        arr[i]= readLine()!!.trim().toInt()
    }
    println("입력하신 숫자의 합은 : ${arr.sum()}")
    println("입력하신 숫자의 합은 : ${arr.average()}")
}