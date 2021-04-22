import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun readLineSplit() = readLine()!!.split(" ").map{it.toString()}
fun readLineTrim() = readLine()!!.trim()

fun main() {
    println("== 게시판 관리 프로그램 시작 ==")

    // 가장 마지막에 입력된 게시물 번호
    var articlesLastId = 0

    val articles = mutableListOf<Article>()

    loop@ while (true) {
        print("명령어) ")
        val command =readLineSplit()
        
        when (command[0]+command[1]) {
            "systemexit" -> {
                println("프로그램을 종료합니다.")
                break@loop
            }
            "articlewrite" -> {
                val id = articlesLastId + 1
                print("제목 : ")
                val title = readLineTrim()
                print("내용 : ")
                val body = readLineTrim()
                val reg = LocalDateTime.now()
                val up = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val forReg=reg.format((formatter))
                val forUp=up.format((formatter))
                val article = Article(id, title, body,forReg,forUp)

                println("${id}번 게시물이 작성되었습니다.")

                articles.add(article)

                articlesLastId = id
            }
            "articlelist" -> {
                println("번호 / 제목")

                for ( article in articles ) {
                    println("${article.id} / ${article.title} / ${article.body} / ${article.regDate} / ${article.updateDate}")
                }
            }

            "articledelete" -> {

                if(articles.any { it.id == command[2].toInt() }) {
                    println("${command[2]} was deleted")
                    articles.removeAt(command[2].toInt() - 1)
                }
                else{
                    println("${command[2]} is not exist")
                }
            }
            "articlemodify" -> {
                println("${command[2]} is modify")

                if(articles.any { it.id == command[2].toInt() }) {
                    print("새제목 : ")
                    val title = readLineTrim()
                    print("새내용 : ")
                    val body = readLineTrim()
                    val num =articles[command[2].toInt()-1].id
                    val currentDateTime= Calendar.getInstance().time
                    val update = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDateTime)
                    val reg=articles[command[2].toInt()-1].regDate
                    val temp=Article(num,title,body,reg,update)
                    articles.removeAt(command[2].toInt()-1)
                    articles.add(command[2].toInt()-1,temp)

                }
                else{
                    println("${command[2]} is not exist")
                }
            }
            else -> {
                println("`$command` 은(는) 존재하지 않는 명령어 입니다.")
            }

        }
    }

    println("== 게시판 관리 프로그램 끝 ==")
}

data class Article(
    val id: Int,
    val title: String,
    val body: String,
    val regDate:String,
    val updateDate:String
)