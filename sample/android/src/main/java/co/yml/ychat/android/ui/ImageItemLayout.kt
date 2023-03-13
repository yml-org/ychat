package co.yml.ychat.android.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.Dimensions.default
import co.yml.ychat.android.ui.Dimensions.robotMessageIconSize
import co.yml.ychat.android.ui.Dimensions.robotMessagePaddingSize
import co.yml.ychat.android.ui.Dimensions.spaceExtraSmall
import co.yml.ychat.android.ui.Dimensions.spaceMedium
import co.yml.ychat.android.ui.Dimensions.spaceSmall
import coil.compose.AsyncImage

@Composable
fun ImageItemLayout(
    messageText: String,
    isOut: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(top = spaceMedium),
            verticalAlignment = Alignment.Bottom
        ) {
            if (isOut.not()) {
                Image(
                    painterResource(R.drawable.ic_robot),
                    contentDescription = "",
                    modifier = Modifier
                        .width(robotMessageIconSize)
                        .height(robotMessageIconSize)
                        .clip(shape = CircleShape)
                        .background(colorResource(id = R.color.softGreen))
                        .padding(robotMessagePaddingSize),
                )
                Spacer(modifier = Modifier.padding(spaceExtraSmall))
            }
            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = spaceMedium,
                            topEnd = spaceMedium,
                            bottomEnd = if (isOut) default else spaceMedium,
                            bottomStart = if (isOut) spaceMedium else default
                        )
                    )
                    .background(if (isOut) colorResource(id = R.color.softBlue) else colorResource(id = R.color.opaqueWhite))
                    .padding(spaceSmall)
            ) {
                AsyncImage(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                    model = messageText,
                    contentDescription = messageText,
                    placeholder = painterResource(R.drawable.ic_robot),
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageItemLayout() {
    MessageItemLayout(messageText = "Message", isOut = false)
}