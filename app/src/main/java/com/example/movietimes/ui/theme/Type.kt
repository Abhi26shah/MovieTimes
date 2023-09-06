package com.example.movietimes.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val ApplicationTypography = Typography(
    body1 = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default
    )
)

val TextBold24 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    color = Color.White
)

val TextSemiBold20 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
    color = Color.White
)

val TextBold16 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold,
    color = Color.White
)

val TextMedium16 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
    color = Color.White
)

val TextRegular16 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    color = Color.White
)

val TextBlackRegular16 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    color = Color.Black
)

val TextRegularHint16 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    color = White75
)

val TextBlueSemiBold18 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
    color = MidBlue
)

val TextSemiBold18 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold
)

val TextSemiBold14 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold
)