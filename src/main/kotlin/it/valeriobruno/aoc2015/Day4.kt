package it.valeriobruno.aoc2015

import com.google.common.hash.Hashing
import java.nio.charset.Charset
import java.security.MessageDigest

class md5Message(var message:String)
{
   fun startWith5zeros() : Boolean
   {
       val hashing= Hashing.md5()
       val hashedTxt = hashing.hashString(message, Charset.forName("UTF-8")).toString()
       println(hashedTxt)

       return hashedTxt.startsWith("00000")
   }
}
fun main()
{
    val key="ckczppom"
    var num=1

    while(! md5Message("$key$num").startWith5zeros()) num++

    println(num)


}