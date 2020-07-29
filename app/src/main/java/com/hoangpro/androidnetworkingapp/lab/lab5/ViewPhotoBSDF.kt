package com.hoangpro.androidnetworkingapp.lab.lab5

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hoangpro.androidnetworkingapp.R
import com.hoangpro.androidnetworkingapp.lab.lab4.PhotoAdapter
import com.hoangpro.androidnetworkingapp.lab.lab4.PhotoData
import com.hoangpro.androidnetworkingapp.util.DialogHelper
import kotlinx.android.synthetic.main.bsdf_view_photo.*

class ViewPhotoBSDF : BottomSheetDialogFragment() {
    private lateinit var photoAdapter: PhotoAdapter
    private var currentPage = 1
    var galleryId = ""
    private var photoData: PhotoData? = null
    private var canLoadMore = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bsdf_view_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initData()
    }

    private fun initData() {
        val dialog = DialogHelper.showDialogLoading(requireContext())
        Lab5Client.getPhotosFromGallery(
            galleryId,
            currentPage,
            onSuccess = {
                if (it != null) {
                    photoData=it
                    photoAdapter.setData(it)
                }
                dialog.dismiss()
            },
            onFailure = {
                dialog.dismiss()
            }
        )
    }

    private fun setupView() {
        photoAdapter = PhotoAdapter(requireContext())
        rvPhoto.apply {
            adapter = photoAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        rvPhoto.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (photoData == null) return
                var arr = IntArray(2){0}
                val manager = rvPhoto.layoutManager as StaggeredGridLayoutManager
                manager.findLastVisibleItemPositions(arr)
                Log.e("last Visible","${arr[1]}")
                if (arr[1] == photoData!!.photos!!.photo!!.size - 1 && canLoadMore && currentPage < photoData!!.photos!!.pages!!) {
                    moveToNextPage()
                }
            }
        })
    }


    fun showFragment(fm: FragmentManager, galleryId: String) {
        this.galleryId = galleryId
        currentPage = 1
        if (!isAdded) {
            show(fm, tag)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val bottomSheetDialog = dialog as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val metrics = DisplayMetrics()
            val frame =
                bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            requireActivity().windowManager.defaultDisplay.getRealMetrics(metrics)
            frame!!.layoutParams.height = metrics.heightPixels
            frame.requestLayout()
            val behavior = BottomSheetBehavior.from(frame)
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> behavior.state =
                            BottomSheetBehavior.STATE_EXPANDED
                    }
                }

            })
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }

    private fun moveToNextPage() {
        canLoadMore = false
        val dialog = DialogHelper.showDialogLoading(requireContext())
        currentPage++
        Lab5Client.getPhotosFromGallery(
            galleryId,
            currentPage,
            onSuccess = {
                if (it != null) {
                    photoAdapter.addData(it)
                }
                dialog.dismiss()
                canLoadMore = true
            },
            onFailure = {
                dialog.dismiss()
                canLoadMore = true
            }
        )
    }
}