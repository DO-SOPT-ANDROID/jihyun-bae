package org.sopt.dosopttemplate.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun ImageView.setImage(imageUrl: String) {
    this.load(imageUrl)
}
