package org.southasia.foodcare.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.southasia.foodcare.vo.request.HouseholdRequest

/**
 * Interface for database access for User related operations.
 */
@Dao
interface HouseholdRequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(householdRequest: HouseholdRequest): Long

    @Update
    fun update(householdRequest: HouseholdRequest): Int

    @Delete
    fun delete(householdRequest: HouseholdRequest)

    @Query("SELECT * FROM household_request WHERE id = :id")
    fun getHouseholdRequest(id: Long): LiveData<HouseholdRequest>

    @Query("SELECT * FROM household_request")
    fun getHouseholdRequests(): LiveData<List<HouseholdRequest>>

}
