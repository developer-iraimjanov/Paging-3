package uz.iraimjanov.paging3compose.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import uz.iraimjanov.paging3compose.util.Constants.UNSPLASH_IMAGE_TABLE

@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var likes: Int,
    @Embedded
    var urls: Urls,
    @Embedded
    var user: User,
)