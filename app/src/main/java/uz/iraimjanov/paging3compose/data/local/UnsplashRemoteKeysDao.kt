package uz.iraimjanov.paging3compose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.iraimjanov.paging3compose.model.UnsplashRemoteKeys

@Dao
interface UnsplashRemoteKeysDao {

    @Query(value = "SELECT * FROM unsplash_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM unsplash_remote_key_table")
    suspend fun deleteAllRemoteKeys()

}