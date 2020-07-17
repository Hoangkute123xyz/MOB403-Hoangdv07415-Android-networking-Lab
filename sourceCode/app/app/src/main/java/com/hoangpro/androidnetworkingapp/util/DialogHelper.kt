package com.hoangpro.androidnetworkingapp.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.hoangpro.androidnetworkingapp.R

class DialogHelper {
    companion object{
        fun showDialogLoading(context: Context):AlertDialog{
            val builder = AlertDialog.Builder(context)
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null,false)
            builder.setView(view)
            val dialog = builder.create()
            dialog.setCancelable(false)
            dialog.show()
            return dialog
        }
    }
}