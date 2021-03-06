package com.plcoding.spotifycloneyt.exoplayer.callbacks

import android.widget.Toast
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.plcoding.spotifycloneyt.exoplayer.MusicService

class MusicPlayerEventListener(
    private val musicService: MusicService
):Player.Listener{
//    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//        super.onPlayerStateChanged(playWhenReady, playbackState)
//        if(playbackState == Player.STATE_READY&&!playWhenReady){
//            musicService.stopForeground(false)
//        }
//    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(musicService,"An unknown error occured",Toast.LENGTH_LONG).show()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        if(playbackState == Player.STATE_READY){
            musicService.stopForeground(false)
        }
    }
// Not sure this one correct or not transform from upper
    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        super.onPlayWhenReadyChanged(playWhenReady, reason)
        if(!playWhenReady){
            musicService.stopForeground(false)
        }
    }
}