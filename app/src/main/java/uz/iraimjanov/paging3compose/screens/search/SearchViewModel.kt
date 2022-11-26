package uz.iraimjanov.paging3compose.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.iraimjanov.paging3compose.data.repository.Repository
import uz.iraimjanov.paging3compose.model.UnsplashImage
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    var searchTextState: State<String> = _searchTextState

    private val _searchedImages = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchedImages = _searchedImages

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch {
            repository.searchImage(query = query).cachedIn(viewModelScope).collect {
                _searchedImages.value = it
            }
        }
    }

}