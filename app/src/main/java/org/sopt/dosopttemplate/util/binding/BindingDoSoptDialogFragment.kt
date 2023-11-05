package org.sopt.dosopttemplate.util.binding

import android.content.DialogInterface
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
    private val clickRightBtn: () -> Unit,
    private val onDialogClosed: () -> Unit
) : BindingDialogFragment<DialogDoSoptBinding>(R.layout.dialog_do_sopt) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        addListeners()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogClosed.invoke()
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