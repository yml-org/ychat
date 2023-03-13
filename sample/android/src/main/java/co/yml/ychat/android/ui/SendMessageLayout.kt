package co.yml.ychat.android.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.MainViewModel
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.Dimensions.spaceExtraLarge
import co.yml.ychat.android.ui.Dimensions.spaceMedium
import co.yml.ychat.android.ui.Dimensions.spaceSmall
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SendMessageLayout() {
    var textFieldState by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val viewModel = koinViewModel<MainViewModel>()
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)


    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(spaceMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(id = R.color.opaqueWhite),
                focusedBorderColor = colorResource(id = R.color.opaqueWhite),
                unfocusedBorderColor = colorResource(id = R.color.opaqueWhite)
            ),
            value = textFieldState,
            label = { Text(text = stringResource(R.string.message)) },
            onValueChange = { textFieldState = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        Column(
            modifier = Modifier
                .padding(top = spaceSmall, start = spaceSmall)
                .size(spaceExtraLarge),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val typingString = stringResource(id = R.string.typing)
            IconButton(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(if (textFieldState.isNotEmpty() && isLoading.not()) colorResource(id = R.color.softBlue) else colorResource(id = R.color.opaqueWhite)),
                onClick = {
                    scope.launch {
                        if (textFieldState.startsWith("/image ")) {
                            viewModel.onImageRequest(textFieldState, typingString)
                        } else {
                            viewModel.onSendMessage(textFieldState, typingString)
                        }
                        textFieldState = ""
                    }
                },
                enabled = textFieldState.isNotEmpty() && isLoading.not()
            ) {
                Icon(
                    imageVector = Icons.Filled.Send, stringResource(R.string.send), tint = MaterialTheme.colors.background
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    SendMessageLayout()
}