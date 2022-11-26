package uz.iraimjanov.paging3compose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import uz.iraimjanov.paging3compose.util.Constants.UNSPLASH_REMOTE_KEY_TABLE

@Entity(tableName = UNSPLASH_REMOTE_KEY_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var prevPage: Int?,
    var nextPage: Int?,
)