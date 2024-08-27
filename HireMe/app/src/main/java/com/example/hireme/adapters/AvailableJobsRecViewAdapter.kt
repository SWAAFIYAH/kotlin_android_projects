package com.example.hireme.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hireme.databinding.AvailableJobsRecItemBinding
import com.example.hireme.models.AvailableJobs

class AvailableJobsRecViewAdapter(
    private val jobs: List<AvailableJobs>,
    // private val onJobClick: (AvailableJobs) -> Unit
) : RecyclerView.Adapter<AvailableJobsRecViewAdapter.JobsViewHolder>() {

    class JobsViewHolder(private val binding: AvailableJobsRecItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: AvailableJobs) {
            binding.jobTitleTV.text = job.title
            binding.companyTV.text = job.company

            /* binding.root.setOnClickListener {
                onJobClick(job)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val binding = AvailableJobsRecItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return JobsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        holder.bind(jobs[position])

    }

    override fun getItemCount(): Int {
        return jobs.size

    }
}
