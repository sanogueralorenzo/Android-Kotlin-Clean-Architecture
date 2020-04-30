package com.sanogueralorenzo.views.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sanogueralorenzo.views.R
import kotlinx.android.synthetic.main.bottom_sheet_container.*

abstract class ContainerBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_container, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sheetRecyclerView.setController(controller)
    }

    abstract val controller: EpoxyController

    protected inline val recyclerView: EpoxyRecyclerView
        get() = sheetRecyclerView
}
