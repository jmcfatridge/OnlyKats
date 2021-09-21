package com.olayg.onlykats.repo

import android.content.Context
import android.util.Log
import com.olayg.onlykats.model.Kat
import com.olayg.onlykats.model.request.Queries
import com.olayg.onlykats.repo.local.KatDatabase
import com.olayg.onlykats.repo.remote.RetrofitInstance
import com.olayg.onlykats.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

// TODO: 9/11/21 Update breeds method to fetch from katService and return correct API state
// TODO: 9/11/21 Update query map to all possible multiple queries
object KatRepo {
    private const val TAG = "KAT-REPO"
    private val katService by lazy { RetrofitInstance.katService }

    suspend fun getKatState(context: Context, queries: Queries) : Flow<List<Kat>> {
        val katDao = KatDatabase.getInstance(context).katDao()
        val katFlow = katDao.getAll()
        val katResponse = katService.getKatImages(queries.asQueryMap)
        if (!katResponse.body().isNullOrEmpty()) katDao.insertAll(*katResponse.body()!!.toTypedArray())
        return katFlow
//        fetchKatState(queries)
//        emit(ApiState.Loading)
//        val state = if (queries.endPoint != null) {
//            val katState = katService.getKatImages(queries.asQueryMap).getApiState()
//            if (katState is ApiState.Success) {
//                KatDatabase.getInstance(context).katDao().insertAll(*katState.data.toTypedArray())
//                ApiState.Success(KatDatabase.getInstance(context).katDao().getAll())
//            } else katState
//        } else ApiState.Failure("Endpoint is null")
//        emit(state)
    }


    fun getBreedState(queries: Queries) = flow {
        emit(ApiState.Loading)
        val state = if (queries.endPoint != null) {
            katService.getBreeds(queries.asQueryMap).getApiState()
        } else ApiState.Failure("Endpoint is null")
        emit(state)
    }

    private fun <S> Response<List<S>>.getApiState(): ApiState<List<S>> {
        return  if (isSuccessful) {
            if(body().isNullOrEmpty()) ApiState.EndOfPage
            else ApiState.Success(body()!!)
        } else ApiState.Failure("Error fetching data!")
    }

    private val Queries.asQueryMap: Map<String, Any>
        get() = listOfNotNull(
            "limit" to limit,
            page?.let { "page" to it }
        ).toMap()
}