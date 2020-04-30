package com.sanogueralorenzo.views.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.sanogueralorenzo.views.R
import kotlinx.android.synthetic.main.bottom_sheet_container.*
import kotlinx.android.synthetic.main.dialog_fragment_container.*

abstract class ContainerDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_fragment_container, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogRecyclerView.setController(controller)
    }

    abstract val controller: EpoxyController

    protected inline val recyclerView: EpoxyRecyclerView
        get() = dialogRecyclerView
}
