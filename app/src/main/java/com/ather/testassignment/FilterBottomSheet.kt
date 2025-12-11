package com.ather.testassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterBottomSheet(
    private val onFilterSelected: (String) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.bottomsheet_filter, container, false)

        val txtAll = view.findViewById<TextView>(R.id.txtAll)
        val txtCompleted = view.findViewById<TextView>(R.id.txtCompleted)
        val txtPending = view.findViewById<TextView>(R.id.txtPending)

        txtAll.setOnClickListener {
            onFilterSelected("all")
            dismiss()
        }
        txtCompleted.setOnClickListener {
            onFilterSelected("completed")
            dismiss()
        }
        txtPending.setOnClickListener {
            onFilterSelected("pending")
            dismiss()
        }

        return view
    }
}
