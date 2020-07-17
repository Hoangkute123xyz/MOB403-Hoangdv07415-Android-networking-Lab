package com.hoangpro.androidnetworkingapp.util

import java.net.URLEncoder
import java.text.Normalizer
import java.util.regex.Pattern

class StringHelper {
    companion object{
        fun toUrlEncode(value:String):String{
            val temp: String = Normalizer.normalize(value, Normalizer.Form.NFD)
            val pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
            return URLEncoder.encode(pattern.matcher(temp).replaceAll(""),"UTF-8")
        }
    }
}