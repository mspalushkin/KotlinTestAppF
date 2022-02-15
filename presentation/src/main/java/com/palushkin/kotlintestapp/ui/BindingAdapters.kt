/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.palushkin.kotlintestapp.common.DomainUser
import com.palushkin.kotlintestapp.R
import com.palushkin.kotlintestapp.ui.home.UserApiStatus
import com.palushkin.kotlintestapp.ui.home.UserListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DomainUser>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data)
}

//@BindingAdapter("userApiStatusImageView")
@BindingAdapter(
    value = ["userApiStatusImageView", "userApiStatusImageViewError"],
    requireAll = true
)
fun bindStatusImage(statusImageView: ImageView, status: UserApiStatus?, properties: List<DomainUser>?) {
    when (status) {
        UserApiStatus.LOADING -> {
            statusImageView.visibility = View.GONE
        }
        UserApiStatus.ERROR -> {
            if (properties != null) {
                if (properties.isEmpty()) {
                    statusImageView.visibility = View.VISIBLE
                }
            }else{
                statusImageView.visibility = View.VISIBLE
            }
            //statusImageView.visibility = View.VISIBLE
            //statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UserApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("userApiStatusProgressBar")
fun bindStatusProgress(progressBarView: ProgressBar, status: UserApiStatus?) {
    when (status) {
        UserApiStatus.LOADING -> {
            progressBarView.visibility = View.VISIBLE
        }
        UserApiStatus.ERROR -> {
            progressBarView.visibility = View.GONE
        }
        UserApiStatus.DONE -> {
            progressBarView.visibility = View.GONE
        }
        else -> {
            progressBarView.visibility = View.GONE
        }
    }
}

@BindingAdapter(value = ["imageUrl", "idProgress"], requireAll = true)
fun bindImage(imgView: ImageView, imgUrl: String?, detailProgressBar: ProgressBar) {
    imgUrl?.let {
        val circularProgress = CircularProgressDrawable(imgView.context)
        circularProgress.strokeWidth = 5f
        circularProgress.centerRadius = 30f
        circularProgress.start()
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                //.circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(CustomListener(detailProgressBar))
                .apply(
                        RequestOptions()
                                //.placeholder(R.drawable.loading_animation)
                                //.placeholder(circularProgress)
                                .error(R.drawable.ic_broken_image)
                )
                .into(imgView)
    }
}

private class CustomListener(detailProgressBar: ProgressBar) : RequestListener<Drawable> {
    val detailProgressBarL = detailProgressBar
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
        detailProgressBarL.visibility = View.GONE
        return false
    }

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        detailProgressBarL.visibility = View.GONE
        return false
    }
}


@BindingAdapter("iconUrl")
fun bindIcon(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imgView)
    }
}