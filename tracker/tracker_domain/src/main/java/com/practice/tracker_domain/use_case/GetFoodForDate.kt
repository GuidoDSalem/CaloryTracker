package com.practice.tracker_domain.use_case

import com.practice.tracker_domain.model.TrackableFood
import com.practice.tracker_domain.model.TrackedFood
import com.practice.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodForDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodForDate(date)
    }

}