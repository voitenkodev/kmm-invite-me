package com.voitenko.dev.kmminviteme.android.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

}
//    val alphaLogo = remember { Animatable(initialValue = 0f) }
//
//    LaunchedEffect(key1 = true) {
//        delay(2000)
//        alphaLogo.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 1000))
//    }
//    ShimmerBox(gradient = listOf(cyan1, cyan2, cyan1)) { rootBrush ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(rootBrush)
//                .padding(top = 44.dp)
//        ) {
//            if (alphaLogo.value < 1f) ListOfEvents(null)
//            else ListOfEvents(events = eventList)
//        }
//    }
//}
//
//@Composable
//fun ListOfEvents(events: List<EventApp>? = null) = if (events == null) {
//    ShimmerBox(gradient = listOf(Color.Gray, Color.LightGray, Color.Gray)) { loadingBrush ->
//        Column {
////            EventDateItem(date = "0000-00-00T00:00", loading = loadingBrush)
//            EventContentItem(loading = loadingBrush)
//            EventContentItem(loading = loadingBrush)
//            EventContentItem(loading = loadingBrush)
//            EventContentItem(loading = loadingBrush)
//        }
//    }
//} else {
//    val grouped = events.groupBy { it.date.toString() }
//    LazyColumn {
////        item { AnimatedFavButton("asdasdsd", modifier = Modifier) }
//
//        grouped.onEachIndexed { index, it ->
//            stickyHeader {
//                val background = if (index % 2 == 0) darkPurple else lightPurple
////                EventDateItem(date = it.key, loading = background)
//            }
//            itemsIndexed(it.value) { _, event ->
////                EventContentItem(event, loading = lightPurple)
//            }
//        }
//    }
//}
//
////@Composable
////private fun EventDateItem(date: String, loading: Brush) = EventDate(
////    modifier = Modifier
////        .padding(vertical = 4.dp, horizontal = 20.dp)
////        .background(shape = RoundedCornerShape(50), brush = loading)
////        .padding(horizontal = 20.dp, vertical = 8.dp),
////    date = date,
////)
//
//@Composable
//private fun EventContentItem(
//    event: EventApp? = null,
//    click: ((EventApp) -> Unit)? = null,
//    loading: Brush
//) = Column(
//    modifier = Modifier
//        .clickable(enabled = click != null, onClick = { event?.let { click?.invoke(it) } })
//        .padding(horizontal = 20.dp, vertical = 8.dp)
//) {
//    val shape = RoundedCornerShape(8.dp)
//    val modifierImg =
//        if (event != null) Modifier.background(color = Color.White, shape = shape)
//        else Modifier.background(brush = loading, shape = shape)
//    val modifierTitle =
//        if (event != null) Modifier
//        else Modifier.background(brush = loading, shape = shape)
//
//    Spacer(modifier = Modifier.height(8.dp))
//}
//
//private val eventList = listOf(
//    EventApp(
//        id = 1,
//        title = "Title of event #1",
//        description = "Description of event #1",
//        date = "2020-08-30T18:43".toLocalDateTime(),
//        imagePath = "https://picsum.photos/200",
//        address = AddressApp(latitude = null, longitude = null, name = "Street of event #1")
//    ),
//    EventApp(
//        id = 2,
//        title = "Title of event #2",
//        description = "Description of event #2",
//        date = "2020-08-30T18:43".toLocalDateTime(),
//        imagePath = "https://picsum.photos/200",
//        address = AddressApp(latitude = null, longitude = null, name = "Street of event #2")
//    ),
//    EventApp(
//        id = 3,
//        title = "Title of event #3",
//        description = "Description of event #3",
//        date = "2021-08-30T18:43".toLocalDateTime(),
//        imagePath = "https://picsum.photos/200",
//        address = AddressApp(latitude = null, longitude = null, name = "Street of event #3")
//    ),
//    EventApp(
//        id = 4,
//        title = "Title of event #4",
//        description = "Description of event #4",
//        date = "2021-08-30T18:43".toLocalDateTime(),
//        imagePath = "https://picsum.photos/200",
//        address = AddressApp(latitude = null, longitude = null, name = "Street of event #4")
//    ),
//    EventApp(
//        id = 5,
//        title = "Title of event #5",
//        description = "Description of event #5",
//        date = "2021-08-30T18:43".toLocalDateTime(),
//        imagePath = "https://picsum.photos/200",
//        address = AddressApp(latitude = null, longitude = null, name = "Street of event #5")
//    ),
//)
