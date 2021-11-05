package com.example.itunesmovies.adapter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesmovies.R
import com.example.itunesmovies.api.OffDB
import com.example.itunesmovies.model.OffDBlistModel
import com.example.itunesmovies.ui.Favorites
import com.example.itunesmovies.utils.getProgressDrawable
import com.example.itunesmovies.utils.loadImage
import kotlinx.android.synthetic.main.delete_to_favorites.*
import kotlinx.android.synthetic.main.favorites_data.view.*
import kotlinx.android.synthetic.main.save_to_favorites.namefav

class FavoritesAdapter(var context: Context, var favOfflist: List<OffDBlistModel>) :
    RecyclerView.Adapter<FavoritesAdapter.DataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataHolder(
        LayoutInflater.from(
        parent.context).inflate(R.layout.favorites_data, parent, false)
    )

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.bind(favOfflist.get(position))
        val favItem = favOfflist.get(position)
        val db = OffDB(context)
        var num: Int

        //Dialog Delete favorite
        val dlgDel = Dialog(context)
        dlgDel.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlgDel.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlgDel.setContentView(R.layout.delete_to_favorites)

        holder.delFav.setOnClickListener {
            num = Integer.parseInt(favItem.mid.toString())

            dlgDel.namefav.text = favItem.primaryGenreName

            //Yes Button = Add to Favorites local database
            dlgDel.btnYesdel?.setOnClickListener {
                val res = db.deleteFavorie(num)
                if (res > -1) {
                    context.startActivity(Intent(context, Favorites::class.java))
                    (context as Activity).finish()
                    Toast.makeText(context, "Deleted to Favorites!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Unable to delete", Toast.LENGTH_LONG).show()
                }
                dlgDel.cancel()
            }

            //No Button = cancel close dialog
            dlgDel.btnNodel?.setOnClickListener {
                dlgDel.cancel()
            }
            dlgDel.show()
        }
    }

    override fun getItemCount(): Int {
        return favOfflist.size
    }

    class DataHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imgFav = v.favimg
        val genreFav = v.favidGenre
        val artistFav = v.favArtisname
        val trackFav = v.favtrackname
        val priceFav = v.favPrice
        val delFav = v.deleteid
        val idfav = v.favMid
        val progressDrawable = getProgressDrawable(v.context)

        fun bind(favlist: OffDBlistModel) {
            artistFav.text = favlist.artistName
            genreFav.text = favlist.primaryGenreName
            trackFav.text = favlist.trackName
            priceFav.text = favlist.trackPrice
            idfav.text = favlist.mid.toString()
            imgFav.loadImage(favlist.urlPicture, progressDrawable)
        }
    }
}