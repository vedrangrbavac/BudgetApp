package com.example.budgetapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.budgetapp.R
import kotlinx.android.synthetic.main.fragment_graph.*
import org.eazegraph.lib.models.PieModel




class GraphFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        piechart.addPieSlice(
            PieModel(
                "R", 25f,
                Color.parseColor("#FFA726")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Python", 35f,
                Color.parseColor("#66BB6A")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "C++", 15f,
                Color.parseColor("#EF5350")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Java", 25f,
                Color.parseColor("#29B6F6")
            )
        )

        piechart.startAnimation()
    }


}