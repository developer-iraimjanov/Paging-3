package uz.iraimjanov.paging3compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.iraimjanov.paging3compose.model.UnsplashImage
import uz.iraimjanov.paging3compose.model.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao

}