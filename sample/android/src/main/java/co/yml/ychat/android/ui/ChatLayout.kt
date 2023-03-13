package co.yml.ychat.android.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import co.yml.ychat.android.MainViewModel
import co.yml.ychat.android.MessageItem
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.Dimensions.spaceLarge
import co.yml.ychat.android.ui.Dimensions.spaceMedium
import co.yml.ychat.android.ui.Dimensions.spaceSmall
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatLayout(
    messages: List<MessageItem>
) {
    val viewModel = koinViewModel<MainViewModel>()
    if (messages.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = spaceLarge, start = spaceMedium, end = spaceMedium),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                modifier = Modifier
                    .padding(Dimensions.robotMessagePaddingSize)
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.opaqueWhite),
                        shape = RoundedCornerShape(corner = CornerSize(spaceMedium))
                    ),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(start = spaceMedium, end = spaceMedium, top = spaceSmall, bottom = spaceSmall),
                    text = stringResource(R.string.enter_any_message_and_chat_gpt_will_answer_it),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.font14).value.sp
                )
            }
        }
        return
    }


    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(spaceMedium),
    ) {
        items(messages) { message ->
            message.url?.let {
                ImageItemLayout(
                    messageText = message.url, isOut = message.isOut
                )
            } ?: run {
                MessageItemLayout(
                    messageText = message.message, isOut = message.isOut
                )
            }
        }
        coroutineScope.launch {
            listState.animateScrollToItem(messages.size)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewChatLayout() {
    ChatLayout(
        messages = listOf(
            MessageItem(message = "message 1", isOut = true),
            MessageItem(message = "message 2", isOut = false)
        )
    )
}