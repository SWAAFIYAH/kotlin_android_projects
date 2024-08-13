package com.example.hireme.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hireme.R
import com.example.hireme.databinding.FragmentDashBoardBinding
import com.google.firebase.firestore.FirebaseFirestore


class DashBoardFragment : Fragment() {
    private lateinit var binding: FragmentDashBoardBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashBoardBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())


        binding.card1.setOnClickListener {
            navController.navigate(R.id.action_dashBoardFragment_to_avaliableJobsFragment)

        }
    }


}