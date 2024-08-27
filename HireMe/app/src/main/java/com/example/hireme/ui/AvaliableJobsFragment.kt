package com.example.hireme.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hireme.R
import com.example.hireme.adapters.AvailableJobsRecViewAdapter
import com.example.hireme.databinding.FragmentAvaliableJobsBinding
import com.example.hireme.models.AvailableJobs
import com.google.firebase.firestore.FirebaseFirestore


class AvaliableJobsFragment : Fragment() {
    private  lateinit var binding: FragmentAvaliableJobsBinding
    private lateinit var db : FirebaseFirestore
    private  lateinit var  navController: NavController
    private lateinit var jobsAdapter: AvailableJobsRecViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAvaliableJobsBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()

        db.collection("jobs").get().addOnSuccessListener { results->
            val jobsList = results.map { document ->
                document.toObject(AvailableJobs::class.java)
            }
            Log.d("Jobs List Size", "Size: ${jobsList.size}")
            Log.d("Jobs List", jobsList.toString())
            jobsAdapter = AvailableJobsRecViewAdapter(jobsList)

            binding.availableJobsRecView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = jobsAdapter
                setHasFixedSize(true)
            }
        }


    }


}