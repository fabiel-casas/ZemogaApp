package com.casas.fabiel.zemogaapp.utils

import android.text.TextUtils

class StringsUtils {
    companion object {
        fun capitalizeFirstLetter(word: String) : String {
            if (!TextUtils.isEmpty(word) && word.length > 0) {
                return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase()
            }
            return ""
        }

        fun capitalizeText(text: String) : String {
            var resultText = ""
            val textArray = text.split(" ")
            for (word in textArray) {
                resultText += capitalizeFirstLetter(word) + " "
            }
            return resultText.trim()
        }
    }
}