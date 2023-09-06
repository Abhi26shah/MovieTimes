package com.example.movietimes.modules.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.movietimes.R
import com.example.movietimes.constants.NavConstants
import com.example.movietimes.dao.CountryCodes
import com.example.movietimes.ui.theme.AppBackground
import com.example.movietimes.ui.theme.LightBlue
import com.example.movietimes.ui.theme.LightGray
import com.example.movietimes.ui.theme.MidBlue
import com.example.movietimes.ui.theme.TextBlackRegular16
import com.example.movietimes.ui.theme.TextBold24
import com.example.movietimes.ui.theme.TextRegular16
import com.example.movietimes.ui.theme.TextSemiBold18
import com.example.movietimes.utility.AppButtonFull
import com.example.movietimes.utility.AppGoogleLoginButton
import com.example.movietimes.utility.AppUtility
import com.example.movietimes.utility.BaseActivity
import com.example.movietimes.utility.DateUtils
import com.example.movietimes.utility.Logger
import com.example.movietimes.utility.NumberEditText
import com.example.movietimes.utility.OTPInputField
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.launch


/**
 * Created by Abhishek Shah on 25 May 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    navigate: (route: String, closePrev: Boolean) -> Unit = { _, _ -> }
) {

    val context = LocalContext.current
    val countryCodes = remember {
        mutableStateListOf<CountryCodes>()
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var phoneNumberWithCountryCode by remember {
        mutableStateOf("")
    }

    var verificationCode by remember {
        mutableStateOf("")
    }

    var resendingToken: PhoneAuthProvider.ForceResendingToken? by remember {
        mutableStateOf(null)
    }

    val phoneAuthCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
//            TODO("Not yet implemented")
            Logger.info("FirebasePhoneAuthentication", "Verification Completed : ${p0.smsCode}")
        }

        override fun onVerificationFailed(p0: FirebaseException) {
//            TODO("Not yet implemented")
            Logger.info("FirebasePhoneAuthentication", "Verification Failed : ${p0.message}")
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)
            Logger.info("FirebasePhoneAuthentication", "Logged In Code Sent")
            verificationCode = p0
            resendingToken = p1
        }

    }

    var selectedCountryCode: CountryCodes by remember {
        mutableStateOf(CountryCodes())
    }

    LaunchedEffect(key1 = Unit, block = {
        countryCodes.addAll(AppUtility(context).getCountryCodes())
        selectedCountryCode =
            if (countryCodes.count {
                    it.code.equals(
                        DateUtils.getDefaultLocale().country,
                        true
                    )
                } > 0) {
                countryCodes[countryCodes.indexOfFirst {
                    it.code.equals(
                        DateUtils.getDefaultLocale().country,
                        true
                    )
                }]
            } else {
                if (countryCodes.size > 0) countryCodes[0] else CountryCodes()
            }
    })
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val showCountryCodeDialog = remember {
        mutableStateOf(false)
    }

    phoneNumberWithCountryCode = "${selectedCountryCode.dialCode}${phoneNumber}"

    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = AppBackground
            )
    ) {

        ModalBottomSheetLayout(
            sheetContent = {
                resendingToken?.let {
                    OTPAuthenticationDialog(
                        verificationCode,
                        phoneNumberWithCountryCode,
                        phoneAuthCallback
                    ) {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                        if (it) {
                            navigate(NavConstants.HomeNavigation.route, true)
                        }
                    }
                }
            }, sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .imePadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(80.dp))

                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.movie_times_logo),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.4f)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = TextBold24
                    )

                    Spacer(modifier = Modifier.height(80.dp))

                    NumberEditText(
                        countryCodeValue = selectedCountryCode,
                        countryCodeClicked = {
                            showCountryCodeDialog.value = true
                        },
                        value = phoneNumber,
                        onValueChanged = {
                            phoneNumber = it
                        },
                        placeholder = stringResource(id = R.string.mobile_number)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    AppButtonFull(
                        text = stringResource(id = R.string.proceed)
                    ) {
                        /*TODO: Implement on button clicked*/
                        BaseActivity.instance?.authenticatePhoneNumber(
                            phoneNumber = phoneNumberWithCountryCode,
                            callback = phoneAuthCallback
                        )
                        coroutineScope.launch {
                            if (!bottomSheetState.isVisible) {
                                bottomSheetState.show()
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(80.dp))
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = stringResource(id = R.string.try_some_other_login_methods),
                        style = TextRegular16
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    AppGoogleLoginButton(
                        text = stringResource(id = R.string.sign_in_with_google)
                    ) {
//                        navigate(Navigation.BackNavigation.name, true)
                        BaseActivity.instance?.authenticateWithGoogleAccount()
                    }

                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }

    if (showCountryCodeDialog.value) {
        CountryCodeDialog(countryCodes = countryCodes, selectedCountryCode) {
            selectedCountryCode = it
            showCountryCodeDialog.value = false
        }
    }
}

@Composable
fun OTPAuthenticationDialog(
    verificationCode: String,
    phoneNumberWithCode: String,
    resendCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
    onBottomSheetClosed: (verified: Boolean) -> Unit = {}
) {
    var showError by remember {
        mutableStateOf(false)
    }

    var otp by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .imePadding()
            .navigationBarsPadding()
            .background(
                color = MidBlue,
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                )
            )
            .padding(16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.otp_verification),
                style = TextSemiBold18,
                color = White
            )

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onBottomSheetClosed(false)
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        OTPInputField(otpLength = 6, value = otp, onValueChanged = {
            otp = it
        }, showError = showError)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "00:59",
                style = TextRegular16,
                color = White
            )

            Text(
                text = stringResource(id = R.string.resend_otp),
                style = TextRegular16,
                color = White,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    BaseActivity.instance?.authenticatePhoneNumber(
                        phoneNumber = phoneNumberWithCode,
                        callback = resendCallback
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        AppButtonFull(text = stringResource(id = R.string.verify_otp)) {
            BaseActivity.instance?.verifyUserOTP(
                otp = otp,
                verificationID = verificationCode) { task ->
                if (task.isSuccessful) {
                    Logger.info("OTPVerification","OTP Verified Successfully")
                    onBottomSheetClosed(true)
                } else {
                    showError = true
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Composable
fun CountryCodeDialog(
    countryCodes: SnapshotStateList<CountryCodes>,
    selectedCountryCode: CountryCodes,
    onDismiss: (selectedCountry: CountryCodes) -> Unit = {}
) {
    var selectedCountry by remember {
        mutableStateOf(selectedCountryCode)
    }

    val countryCodeList = remember {
        mutableStateListOf<CountryCodes>().apply {
            addAll(countryCodes)
        }
    }

    var searchQuery by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = { onDismiss(selectedCountry) },
        properties = DialogProperties(
            decorFitsSystemWindows = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .background(
                    color = White,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Column(Modifier.fillMaxSize()) {
                TextField(
                    value = searchQuery, onValueChange = { searchedValue ->
                        searchQuery = searchedValue
                        countryCodeList.clear()
                        countryCodeList.addAll(
                            countryCodes.filter {
                                it.name.contains(searchQuery, ignoreCase = true)
                                        || it.code.contains(searchQuery, ignoreCase = true)
                                        || it.dialCode.contains(searchQuery, ignoreCase = true)
                            }
                        )
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.search), color = Color.Gray)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Black,
                        cursorColor = Color.Black,
                        placeholderColor = Color.Gray
                    ),
                    textStyle = TextBlackRegular16,
                    singleLine = true
                )
                LazyColumn(content = {
                    items(countryCodeList) {
                        Column(modifier = Modifier) {
                            Column(modifier = Modifier
                                .background(
                                    color = if (it == selectedCountryCode) {
                                        LightBlue
                                    } else {
                                        White
                                    }
                                )
                                .clickable {
                                    selectedCountry = it
                                    onDismiss(selectedCountry)
                                }
                                .padding(vertical = 4.dp, horizontal = 12.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = it.dialCode,
                                        style = TextRegular16,
                                        color = Color.Black,
                                        modifier = Modifier.weight(0.2f)
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        text = it.name,
                                        style = TextRegular16,
                                        color = Color.Black,
                                        modifier = Modifier.weight(0.8f)
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        color = LightGray
                                    )
                                    .padding(vertical = 4.dp, horizontal = 12.dp)
                            )
                        }
                    }
                })
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    CountryCodeDialog(SnapshotStateList(), CountryCodes())
}