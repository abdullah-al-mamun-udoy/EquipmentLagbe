package com.metamamun.equipment.presentation.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val keyword = rememberSaveable {
        mutableStateOf("")
    }
    val isGenreSectionVisible = rememberSaveable {
        mutableStateOf(true)
    }

/*    val searchContentsState = viewModel.searchedContents.collectAsState()
    val searchContentsList = mutableListOf<ContentInfo>()

    searchContentsState.value.apply {
        when (this) {
            is Resource.Success -> {
                    searchContentsList.clear()
                    searchContentsList.addAll((this.data?.contents ?: emptyList()) as Collection<ContentInfo>)
                    isGenreSectionVisible.value = false
            }
            is Resource.Failure -> {
                // Handle failure
            }
            is Resource.Loading -> {
                // Handle loading
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            keyword = keyword,
            onSearch = {
                viewModel.getSearchContentList(keyword.value)
            }
        )
        
        if (isGenreSectionVisible.value) {
            GenresSection(
                onGenreClick = {
                    keyword.value = it.title
                }
            )
        }else{
            CustomText(
                text = "${searchContentsList.size} Results Found For '${keyword.value}'",
                style = MaterialTheme.typography.titleMedium,
                color = BlackLight,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            SearchResults(
                contents = searchContentsList
            )
        }
    }*/
}