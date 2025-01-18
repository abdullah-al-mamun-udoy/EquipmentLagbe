package com.metamamun.equipment.presentation.screen.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
//    private val searchContentService: SearchContentService,
) : ViewModel() {

//    private val _searchedContents = MutableStateFlow<Resource<ContentVideosBean?>>(Resource.Loading)
//    val searchedContents = _searchedContents.asStateFlow()
//
//    fun getSearchContentList(keyword: String) {
//        viewModelScope.launch {
//            val response = resultFromResponse {
//                searchContentService.loadData(
//                    keyword = keyword
//                )
//            }
//            _searchedContents.value = response
//        }
//    }
}