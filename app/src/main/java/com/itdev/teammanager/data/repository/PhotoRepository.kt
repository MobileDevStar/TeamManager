package com.itdev.teammanager.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itdev.teammanager.api.ApiService
import com.itdev.teammanager.data.PhotoPagingSource
import com.itdev.teammanager.data.model.MemberPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val service: ApiService) {
    fun getSearchResultStream(query: String): Flow<PagingData<MemberPhoto>> {
        return Pager (
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {PhotoPagingSource(service, query)}
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}