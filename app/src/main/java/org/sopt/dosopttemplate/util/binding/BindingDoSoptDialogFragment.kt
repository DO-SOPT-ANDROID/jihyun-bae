package org.sopt.dosopttemplate.util.binding

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.DialogDoSoptBinding

class BindingDoSoptDialogFragment(
    @DrawableRes
    private val icon: Int,
    private val title: String,
    private val context: String,
    private val leftBtnText: String,
    private val rightBtnText: String,
    private val clickLeftBtn: () -> Unit,
    private val clickRightBtn: () -> Unit
) : BindingDialogFragment<DialogDoSoptBinding>(R.layout.dialog_do_sopt) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        with(binding) {
            ivDoSoptDialogIcon.setImageResource(icon)
            tvDoSoptDialogTitle.text = title
            tvDoSoptDialogContext.text = context
            btnDoSoptDialogLeft.text = leftBtnText
            btnDoSoptDialogRight.text = rightBtnText
        }
    }

    private fun addListeners() {
        with(binding) {
            btnDoSoptDialogLeft.setOnClickListener {
                clickLeftBtn.invoke()
                dismiss()
            }

            btnDoSoptDialogRight.setOnClickListener {
                clickRightBtn.invoke()
                dismiss()
            }
        }
    }
}