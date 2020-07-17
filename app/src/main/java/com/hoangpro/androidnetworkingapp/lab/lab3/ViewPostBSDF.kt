package com.hoangpro.androidnetworkingapp.lab.lab3

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.core.text.HtmlCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hoangpro.androidnetworkingapp.R
import kotlinx.android.synthetic.main.bsdf_view_post.*

class ViewPostBSDF() : BottomSheetDialogFragment(){
    lateinit var postDetail:PostData.PostDetail
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bsdf_view_post,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        webView.loadDataWithBaseURL(null,postDetail.content,"text/html","UTF-8",null)
        tvTitle.text = postDetail.title
        tvTime.text = postDetail.date
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = dialog as BottomSheetDialog
            val frame = bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(frame!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }

}