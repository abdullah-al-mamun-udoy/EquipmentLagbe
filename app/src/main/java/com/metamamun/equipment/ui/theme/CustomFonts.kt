package com.metamamun.equipment.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.metamamun.equipment.R

object CustomFonts {
    val fonts = FontFamily(
        Font(R.font.archivo_condensed_regular, weight = FontWeight.Normal),
        Font(R.font.archivo_condensed_light, weight = FontWeight.Light),
        Font(R.font.archivo_condensed_medium, weight = FontWeight.Medium),
        Font(R.font.archivo_condensed_bold, weight = FontWeight.Bold),
        Font(R.font.archivo_condensed_semibold, weight = FontWeight.SemiBold),
    )

    val archivoRegular = FontFamily(Font(R.font.archivo_condensed_regular))
    val archivoLight = FontFamily(Font(R.font.archivo_condensed_light))
    val archivoMedium = FontFamily(Font(R.font.archivo_condensed_medium))
    val archivoBold = FontFamily(Font(R.font.archivo_condensed_bold))
    val archivoSemiBold = FontFamily(Font(R.font.archivo_condensed_semibold))
}