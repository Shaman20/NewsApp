package com.example.devnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class NewsAdapter(private val mNews: List<Article>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_resource, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = mNews[position]
        holder.tvTitle.text = currentItem.title
        holder.tvAuthor.text = currentItem.author
        Glide.with(holder.itemView)
            .load(currentItem.urlToImage)
            .apply(RequestOptions().transform(CircleCrop()))
            .into(holder.ivImage)

        holder.itemView.setOnClickListener {

            val colorPrimary = R.drawable.gradient_background

            val intent = CustomTabsIntent.Builder()
                .setDefaultColorSchemeParams(
                    CustomTabColorSchemeParams.Builder()
                        .setToolbarColor(colorPrimary)
                    .build())
                .setUrlBarHidingEnabled(true)
                .setShowTitle(true)
                .build()
            intent.launchUrl(context, Uri.parse(currentItem.url))
        }
    }

    override fun getItemCount(): Int {
        return mNews.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        val ivImage: ImageView = itemView.findViewById(R.id.imageView)

    }
}