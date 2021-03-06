package org.bandev.buddhaquotescompose.scenes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Brightness6
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.alorma.settings.composables.SettingsMenuLink
import org.bandev.buddhaquotescompose.R
import org.bandev.buddhaquotescompose.items.Option
import org.bandev.buddhaquotescompose.settings.Settings
import org.bandev.buddhaquotescompose.settings.SettingsViewModel
import org.bandev.buddhaquotescompose.settings.iconify
import org.bandev.buddhaquotescompose.settings.stringify

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsScene(viewModel: SettingsViewModel = SettingsViewModel(LocalContext.current)) {

    var openDialog by remember { mutableStateOf(false) }

    val theme = viewModel.getThemeLive()

    val options = arrayOf(
        Option(icon = Icons.Rounded.LightMode, stringRes = R.string.light, theme = Settings.Theme.LIGHT),
        Option(icon = Icons.Rounded.DarkMode, stringRes = R.string.dark, theme = Settings.Theme.DARK),
        Option(icon = Icons.Rounded.Brightness6, stringRes = R.string.system, theme = Settings.Theme.SYSTEM)
    )

    Column {
        SettingsMenuLink(
            modifier = Modifier.background(MaterialTheme.colors.background),
            icon = { Icon(imageVector = theme.iconify(), contentDescription = null) },
            title = { Text(text = stringResource(R.string.theme)) },
            subtitle = { Text(text = theme.stringify()) },
            onClick = { openDialog = true },
        )
    }

    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            LazyColumn(
                Modifier
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                items(options) { option ->
                    Box(
                        Modifier
                            .wrapContentHeight()
                            .width(width = 500.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                color = Color.Transparent,
                                shape = MaterialTheme.shapes.large
                            )
                            .clickable {
                                openDialog = false
                                viewModel.setTheme(option.theme)
                            },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(Modifier.padding(horizontal = 20.dp, vertical = 30.dp)) {
                            Icon(
                                imageVector = option.icon,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(50.dp))
                            Text(
                                text = stringResource(id = option.stringRes),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}