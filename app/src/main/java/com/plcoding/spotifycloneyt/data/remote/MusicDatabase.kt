package com.plcoding.spotifycloneyt.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.plcoding.spotifycloneyt.other.Constants.SONG_COLLECTION
import com.plcoding.spotifycloneyt.data.entites.Song
import kotlinx.coroutines.tasks.await

class MusicDatabase{

    private val firebase = FirebaseFirestore.getInstance()
    private val songCollection = firebase.collection(SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song>{
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        }catch (e:Exception){
            emptyList()
        }
    }
}