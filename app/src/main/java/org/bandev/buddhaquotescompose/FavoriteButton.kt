package org.bandev.buddhaquotescompose

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun FavoriteButton(
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    IconToggleButton(
        checked = isChecked,
        onCheckedChange = { onClick() },
        modifier = modifier
    ) {
        val transition = updateTransition(isChecked, label = "Checked indicator")

        val tint by transition.animateColor(
            label = "Tint"
        ) { isChecked ->
            if (isChecked) Color.Red else Color.White
        }

        val size by transition.animateDp(
            transitionSpec = {
                if (false isTransitioningTo true) {
                    keyframes {
                        durationMillis = 250
                        30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                        35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                        40.dp at 75 // ms
                        35.dp at 150 // ms
                    }
                } else {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
            },
            label = "Size"
        ) { 30.dp }

        Icon(
            imageVector = if (isChecked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )
    }
}