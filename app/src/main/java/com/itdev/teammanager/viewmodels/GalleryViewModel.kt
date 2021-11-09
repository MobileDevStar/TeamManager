package com.itdev.teammanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itdev.teammanager.data.model.MemberPhoto
import com.itdev.teammanager.data.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<MemberPhoto>>? = null

    fun searchPictures(queryString: String): Flow<PagingData<MemberPhoto>> {
        currentQueryValue = queryString

        val newResult: Flow<PagingData<MemberPhoto>> = repository.getSearchResultStream(queryString).cachedIn(viewModelScope)

        currentSearchResult = newResult

        return newResult
    }
}