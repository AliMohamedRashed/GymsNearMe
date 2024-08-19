package com.ali.gymsnearme.gyms.presentation.gymslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ali.gymsnearme.gyms.model.Gym
import com.ali.gymsnearme.ui.theme.Purple80


@Composable
fun GymScreen(
    state: GymsScreenState,
    onItemClicked: (Int) -> Unit,
    onFavouriteIconClicked:(id:Int, oldValue:Boolean) -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn {
            items(state.gyms){ gym ->
                GymItem(
                    gym = gym,
                    onFavouriteIconClicked = { id, oldValue -> onFavouriteIconClicked(id,oldValue)},
                    onItemClicked ={ id-> onItemClicked(id) }
                )
            }
        }
        if (state.isLoading) CircularProgressIndicator()
        state.error?.let { Text(text = it) }
    }

//    Column(Modifier.verticalScroll(rememberScrollState())) {
//        listOfGyms.forEach {
//            GymItem(it)
//        }
//    }
}

@Composable
fun GymItem(gym: Gym, onFavouriteIconClicked: (Int, Boolean) -> Unit, onItemClicked: (Int) -> Unit) {

    val icon = if (gym.isFavourite) {
        Icons.Filled.Favorite
    } else {
        Icons.Filled.FavoriteBorder
    }
    Card(elevation = 4.dp,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onItemClicked(gym.id) }) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            DefaultIcon(Icons.Filled.Place, Modifier.weight(0.15f), "Location Icon")
            GymDetails(gym, Modifier.weight(0.70f))
            DefaultIcon(icon, Modifier.weight(0.15f), "Favourite Gym Icon") {
                onFavouriteIconClicked(gym.id,gym.isFavourite)
            }
        }
    }
}
@Composable
fun DefaultIcon(icon: ImageVector, modifier: Modifier, contentDescription: String, onClick: ()-> Unit = {}) {
    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClick()
            },
        colorFilter = ColorFilter.tint(
            Color.DarkGray
        )

    )
}

@Composable
fun GymDetails(gym: Gym, modifier: Modifier, horizontalAlignment: Alignment.Horizontal = Alignment.Start) {
    Column(modifier = modifier,horizontalAlignment = horizontalAlignment) {
        Text(
            text = gym.name,
            style = MaterialTheme.typography.h6,
            color = Purple80
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = gym.place,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}