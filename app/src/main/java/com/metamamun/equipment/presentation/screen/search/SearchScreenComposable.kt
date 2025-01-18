//package com.lazydeveloper.fit4u2.presentation.screen.search
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.Icon
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.platform.LocalSoftwareKeyboardController
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.lazydeveloper.fit4u2.R
//import com.lazydeveloper.fit4u2.model.content.ContentInfo
//import com.lazydeveloper.fit4u2.presentation.composables.CustomImageAsync
//import com.lazydeveloper.fit4u2.presentation.composables.CustomText
//import com.lazydeveloper.fit4u2.ui.theme.BlackLight
//import com.lazydeveloper.fit4u2.ui.theme.padding
//import kotlinx.coroutines.job
//
//
//@Preview
//@Composable
//fun SearchTextField(
//    modifier: Modifier = Modifier,
//    keyword: MutableState<String> = mutableStateOf(""),
//    onSearch: (() -> Unit)? = null,
//) {
//    val focusRequester = remember { FocusRequester() }
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusManager = LocalFocusManager.current
//
//    BasicTextField(
//        value = keyword.value,
//        onValueChange = { newText: String -> keyword.value = newText },
//        modifier = modifier
//            .padding(horizontal = 20.dp, vertical = 10.dp)
//            .padding(top = 10.dp)
//            .focusRequester(focusRequester),
//        textStyle = TextStyle(
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Normal,
//            fontFamily = FontFamily(Font(R.font.archivo_condensed_regular)),
//            color = Color(0xFFA8A8A8)
//        ),
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
//        keyboardActions = KeyboardActions(onSearch = {
//            keyboardController?.hide()
//            focusManager.clearFocus()
//            onSearch?.invoke()
//        }),
//        cursorBrush = SolidColor(colorResource(id = R.color.black)),
//        decorationBox = { innerTextField ->
//            Row(
//                modifier = modifier
//                    .height(48.dp)
//                    .fillMaxWidth()
//                    .background(
//                        color = Color(0xFFF7F7F7),
//                        shape = RoundedCornerShape(size = 10.dp)
//                    )
//                    .padding(horizontal = 12.dp), // inner padding
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_search),
//                    contentDescription = "Search icon",
//                    tint = Color(0xFFA8A8A8)
//                )
//                Spacer(modifier = Modifier.width(width = 8.dp))
//                Box(
//                    modifier = modifier.fillMaxSize(),
//                    contentAlignment = Alignment.CenterStart
//                ) {
//                    if (keyword.value.isBlank()) {
//                        Text(
//                            text = "Enter title to search",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Normal,
//                            fontFamily = FontFamily(Font(R.font.archivo_condensed_regular)),
//                            color = Color(0xFFA8A8A8)
//                        )
//                    }
//                    innerTextField()
//                }
//            }
//        }
//    )
//    LaunchedEffect(Unit) {
//        this.coroutineContext.job.invokeOnCompletion { focusRequester.requestFocus() }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchResults(
//    contents: MutableList<ContentInfo>? = null,
//){
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        contents?.let {
//            items(it.size) { index ->
//                SearchedItem(content = contents[index])
//            }
//        }
//        item {
//            if (contents.isNullOrEmpty()) {
//                CustomText(
//                    text = "No results found",
//                    textAlign = TextAlign.Center,
//                    color = BlackLight,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = padding.tripleExtraLarge2),
//                    style = MaterialTheme.typography.titleMedium,
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchedItem(
//    content: ContentInfo? = null
//){
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(150.dp)
//            .padding(horizontal = 16.dp)
//            .padding(bottom = 16.dp)
//            .background(Color.White),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        ),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            CustomImageAsync(
//                imageUrl = content?.featuredBanner ?: "",
//                modifier = Modifier
//                    .padding(start = 16.dp, top = 22.dp, bottom = 16.dp, end = 16.dp)
//                    .width(125.92.dp)
//                    .height(90.28.dp)
//            )
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                CustomText(
//                    text = content?.contentName ?: "",
//                    style = MaterialTheme.typography.labelMedium,
//                    color = BlackLight.copy(alpha = 0.5f)
//                )
//                CustomText(
//                    text = content?.tags ?: "",
//                    style = MaterialTheme.typography.titleLarge,
//                    color = BlackLight
//                )
//                CustomText(
//                    text = content?.contentPublishTime ?: "",
//                    style = MaterialTheme.typography.labelSmall,
//                    color = BlackLight.copy(alpha = 0.4f)
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GenresSection(
//    onGenreClick: (GenreModel) -> Unit = {}
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 20.dp)
//    ) {
//        Text(
//            text = "Genres",
//            modifier = Modifier.padding(start = 25.dp),
//            style = TextStyle(
//                fontSize = 16.sp,
//                color = Color(0xFFD00234)
//            )
//        )
//
//        LazyVerticalGrid(
//            modifier = Modifier.padding(horizontal = 20.dp),
//            columns = GridCells.Fixed(2),
//            content = {
//                items(items.size) {
//                    GenreItem(items[it],
//                        clickEvent = {
//                            onGenreClick.invoke(it)
//                        })
//                }
//            })
//
//    }
//}
//
//@Composable
//fun GenreItem(
//    genreModel: GenreModel,
//    clickEvent: (GenreModel) -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 5.dp, vertical = 5.dp)
//            .background(
//                color = Color(0xFFF7F7F7),
//                shape = RoundedCornerShape(size = 5.dp)
//            )
//            .clickable {
//                clickEvent.invoke(genreModel)
//            }
//            .padding(20.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = genreModel.title,
//            modifier = Modifier
//                .padding(start = 10.dp)
//                .fillMaxWidth(),
//            style = TextStyle(
//                fontSize = 14.sp,
//                lineHeight = 18.sp,
//                fontFamily = FontFamily(Font(R.font.archivo_condensed_regular)),
//                color = Color(0xFFA8A8A8),
//                letterSpacing = 0.16.sp,
//            )
//        )
//    }
//}
//
//
//data class GenreModel(
//    val id: Int = 0,
//    val title: String,
//)
//
//val items = listOf(
//    GenreModel(
//        id = 0,
//        title = "IPL 2024 Live",
//
//        ),
//    GenreModel(
//        id = 1,
//        title = "Sports",
//
//        ),
//    GenreModel(
//        id = 2,
//        title = "Mlb scores",
//
//        ),
//    GenreModel(
//        id = 3,
//        title = "football scores",
//    ),
//    GenreModel(
//        id = 4,
//        title = "bbc sport football",
//    ),
//    GenreModel(
//        id = 5,
//        title = "epl results",
//    ),
//    GenreModel(
//        id = 6,
//        title = "world cup",
//    ),
//)
//
//
