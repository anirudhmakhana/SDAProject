package com.plcoding.spotifycloneyt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.data.entites.Song
import com.plcoding.spotifycloneyt.databinding.ListItemBinding
//import kotlinx.android.synthetic.main.list_item.view.* // remove move to binding
import javax.inject.Inject

class SongAdapter @Inject constructor(private val glide: RequestManager)
    : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
    class SongViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
    //old-->class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        //try use binding//
        val binding: ListItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return SongViewHolder(binding)
        //---------------//
        // return SongViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.itemView.apply{
            //change reference
            holder.binding.tvPrimary.text = song.title
            holder.binding.tvSecondary.text = song.subtitle
            glide.load(song.imageUrl).into(holder.binding.ivItemImage)
            //---------------
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }
    private var onItemClickListener: ((Song) -> Unit)? = null

    fun setOnItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}