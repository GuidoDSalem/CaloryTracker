package com.practice.tracker_data.repository

import com.practice.tracker_data.local.TrackerDao
import com.practice.tracker_data.mapper.toTrackableFood
import com.practice.tracker_data.mapper.toTrackedFood
import com.practice.tracker_data.mapper.toTrackedFoodEntity
import com.practice.tracker_data.remote.OpenFoodApi
import com.practice.tracker_domain.model.TrackableFood
import com.practice.tracker_domain.model.TrackedFood
import com.practice.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try{
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(searchDto.products.mapNotNull { product ->
                product.toTrackableFood()
            })
        }catch(e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map{entities ->
            entities.map{ it.toTrackedFood()}
        }
    }
}