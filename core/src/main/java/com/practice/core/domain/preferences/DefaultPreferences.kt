package com.practice.core.domain.preferences

import android.content.SharedPreferences
import com.practice.core.domain.model.ActivityLevel
import com.practice.core.domain.model.Gender
import com.practice.core.domain.model.GoalType
import com.practice.core.domain.model.UserInfo
import com.practice.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit()
            .putString(Preferences.KEY_GENDER,gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_AGE,age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_WEIGHT,weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_HEIGHT,height)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL,level.name)
            .apply()
    }

    override fun saveGoalType(type: GoalType) {
        sharedPref.edit()
            .putString(Preferences.KEY_GOAL_TYPE,type.name)
            .apply()
    }

    override fun saveCarbsRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_ACTIVITY_LEVEL,ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO,ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_FAT_RATIO,ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(Preferences.KEY_AGE,-1)
        val height = sharedPref.getInt(Preferences.KEY_HEIGHT,-1)
        val weight = sharedPref.getFloat(Preferences.KEY_WEIGHT,-1f)
        val genderString = sharedPref.getString(Preferences.KEY_GENDER,null)
        val activityLevelString = sharedPref.getString(Preferences.KEY_ACTIVITY_LEVEL,null)
        val goalTypeString = sharedPref.getString(Preferences.KEY_GOAL_TYPE,null)
        val carbRatio = sharedPref.getFloat(Preferences.KEY_CARB_RATIO,-1f)
        val proteinRatio = sharedPref.getFloat(Preferences.KEY_PROTEIN_RATIO,-1f)
        val fatRatio = sharedPref.getFloat(Preferences.KEY_FAT_RATIO,-1f)


        return UserInfo(
            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalTypeString ?: "keep_weight"),
            proteinRatio = proteinRatio,
            fatRatio = fatRatio,
            carbRatio = carbRatio

        )


    }

    override fun saveShouldShowOnBoarding(shouldShow: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING,shouldShow).apply()
    }

    override fun loadShouldShowOnBoarding(): Boolean {
        return sharedPref.getBoolean(
            Preferences.KEY_SHOULD_SHOW_ONBOARDING,true
        )
    }

}