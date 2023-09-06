package com.example.movietimes.utility

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.movietimes.R
import com.example.movietimes.dao.CountryCodes
import com.example.movietimes.ui.theme.ApplicationTypography
import com.example.movietimes.ui.theme.GoogleBlue
import com.example.movietimes.ui.theme.MidBlue
import com.example.movietimes.ui.theme.OFFWhite12
import com.example.movietimes.ui.theme.TextBlueSemiBold18
import com.example.movietimes.ui.theme.TextMedium16
import com.example.movietimes.ui.theme.TextRegularHint16
import com.example.movietimes.ui.theme.TextSemiBold18
import com.example.movietimes.ui.theme.White12
import com.example.movietimes.ui.theme.White50


/**
 * Created by Abhishek Shah on 29 May 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */

@Composable
fun NumberEditText(
    countryCodeValue: CountryCodes,
    countryCodeClicked: () -> Unit,
    value: String,
    onValueChanged: (text: String) -> Unit = {},
    placeholder: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .border(
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
                width = 2.dp
            )
            .background(
                color = White12,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(12.dp, 1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = countryCodeValue.dialCode, style = TextMedium16,
        modifier = Modifier.clickable {
            countryCodeClicked()
        })
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .width(1.dp)
                .background(
                    color = Color.White
                )
                .border(
                    width = 1.dp,
                    color = Color.White
                )
        )
        TextField(
            value = value, onValueChange = onValueChanged,
            placeholder = {
                Text(text = placeholder, style = TextRegularHint16)
            }, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White,
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent
                ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AppButtonFull(text: String, onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(50.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MidBlue
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = text,
            style = TextBlueSemiBold18,
            modifier = Modifier.padding(vertical = 5.dp)
        )

    }
}

@Composable
fun AppGoogleLoginButton(text: String, onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(50.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MidBlue
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                GifImageView(
                    image = R.drawable.google_loader,
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                )
            }

            Text(
                text = text,
                style = TextSemiBold18,
                color = GoogleBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .fillMaxWidth()
            )
        }

    }
}

@Composable
fun GifImageView(image: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = image).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
@Composable
fun OTPInputField(
    otpLength: Int,
    value: String,
    onValueChanged: (value: String) -> Unit = {},
    showError: Boolean = false
) {
    val focusRequester = remember {
        FocusRequester()
    }

    val isKeyboardOpen = remember {
        mutableStateOf(false)
    }

    isKeyboardOpen.value = WindowInsets.isImeVisible

    val checkKeyboardPresence = remember {
        mutableStateOf(false)
    }

    if (checkKeyboardPresence.value) {
        LocalSoftwareKeyboardController.current?.show()
        checkKeyboardPresence.value = false
    }

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen.value) {
            focusRequester.freeFocus()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(IntrinsicSize.Min)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {

//                    if (showError) {
//
//                        onValueChanged("")
//                    } else {
                Logger.error("onValueChange", "normal")
                if (it.length <= otpLength) {
                    onValueChanged(it)
                } else {
                    onValueChanged(value)
                }
//                    }
            },
            modifier = Modifier
                .alpha(0F)
                .height(40.dp)
                .width(0.dp)
                .focusRequester(focusRequester = focusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true
        ) {
            Text(text = value)
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) {
                    checkKeyboardPresence.value = true
                    focusRequester.requestFocus()
                }) {
            repeat(otpLength) { index ->
                val char = when {
                    index >= value.length -> ""
                    else -> value[index].toString()
                }

                Text(
                    text = char,
                    modifier = Modifier
                        .border(
                            1.dp, if (showError) {
                                Color.Red
                            } else {
                                Color.White
                            }, RoundedCornerShape(8.dp)
                        )
                        .width(40.dp)
                        .fillMaxHeight()
                        .padding(2.dp),
                    style = ApplicationTypography.h5,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TextInputField(
    value: String,
    onValueChanged: (text: String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    TextField(value = value, onValueChange = onValueChanged,
    modifier = modifier.fillMaxWidth().clip(AbsoluteRoundedCornerShape(7.dp)),
    colors = TextFieldDefaults.textFieldColors(
        placeholderColor = White50,
        backgroundColor = OFFWhite12,
        cursorColor = Color.White,
        textColor = Color.White,
        focusedIndicatorColor = Color.White
    ),
        placeholder = {
            Text(text = placeholder,
            color = White50)
        }
    )
}

inline fun<T,K> LazyListScope.items(
    items: MutableMap<T,K>,
    noinline key: ((item: MutableMap.MutableEntry<T, K>) -> Any)? = null,
    noinline contentType: (item: MutableMap.MutableEntry<T, K>) -> Any? = { null },
    crossinline itemContent: @Composable() (LazyItemScope.(item: MutableMap.MutableEntry<T, K>) -> Unit)
) {
    val mapData = items.entries
    items(
        count = mapData.size,
        key = if (key != null) { index: Int -> key(mapData.elementAt(index)) } else null,
        contentType = { index: Int -> contentType(mapData.elementAt(index)) }) {
        itemContent(mapData.elementAt(it))
    }
}

@Composable
fun loadRemoteImage(context: Context, url: String): AsyncImagePainter {
    return rememberAsyncImagePainter (
        remember(url) {
            ImageRequest.Builder(context)
                .data(url)
                .allowConversionToBitmap(true)
                .build()
        }
    )
}

@Preview
@Composable
fun ComponentPreview() {
    TextInputField(value = "",{},"Hello")
}