import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun readLineTrim() = readLine()!!.trim()

fun main() {
    println("== 게시판 관리 프로그램 시작 ==")

    // 가장 마지막에 입력된 게시물 번호
    var articlesLastId = 0

    val articles = mutableListOf<Article>()

    loop@ while (true) {
        print("명령어) ")
        val command = readLineTrim()

        when (command) {
            "system exit" -> {
                println("프로그램을 종료합니다.")
                break@loop
            }
            "article write" -> {
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
            "article list" -> {
                println("번호 / 제목")

                for ( article in articles ) {
                    println("${article.id} / ${article.title} / ${article.body} / ${article.regDate} / ${article.updateDate}")
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