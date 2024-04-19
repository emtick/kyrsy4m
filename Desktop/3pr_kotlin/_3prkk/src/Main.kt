//1 задача
fun main() {
    println(agoToText(30)) // только что
    println(agoToText(3600)) // 1 час назад
    println(agoToText(7205)) // 2 часа назад
    println(agoToText(86405)) // вчера
    println(agoToText(172805)) // позавчера
    println(agoToText(259205)) // давно

}
fun agoToText(seconds: Int): String {
    return when {
        seconds in 0..60 -> "был(а) только что"
        seconds in 61..3600 -> "${minutesToText(seconds / 60)} назад"
        seconds in 3601..86400 -> "${hoursToText(seconds / 3600)} назад"
        seconds in 86401..172800 -> "вчера"
        seconds in 172801..259200 -> "позавчера"
        else -> "давно"
    }
}

fun minutesToText(minutes: Int): String {
    return when {
        minutes in 1..4 || minutes in 21..24 || minutes in 31..34 || minutes in 41..44 || minutes in 51..54 -> "$minutes минуту"
        minutes in 5..20 || minutes in 25..30 || minutes in 35..40 || minutes in 45..50 || minutes in 55..59 -> "$minutes минут"
        else -> "$minutes минуты"
    }
}

fun hoursToText(hours: Int): String {
    return when {
        hours == 1 || hours == 21 -> "$hours час"
        hours in 2..4 || hours in 22..24 -> "$hours часа"
        else -> "$hours часов"
    }
}
fun calculateTransferFee(cardType: String = "VK Pay", previousTransfers: Int = 0, transfer: Int): Any {
    if(cardType == "MasterCard" || cardType == "Maestro") {
        if(previousTransfers + transfer > 300) {
            return transfer * 0.01
        } else {
            return 0
        }
    } else {
        return 0
    }
    val transferFee = calculateTransferFee("MasterCard", 200, 1000)
    println("Плата за перевод: $transferFee рублей")
}

