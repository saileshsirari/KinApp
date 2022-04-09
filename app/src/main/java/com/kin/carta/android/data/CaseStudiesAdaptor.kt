package com.kin.carta.android.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kin.carta.android.CaseStudiesFragmentDirections
import com.kin.carta.android.CaseStudiesItemViewModel
import com.kin.carta.android.R
import com.kin.carta.android.databinding.ListItemCaseStudyBinding

class CaseStudiesAdaptor :
    PagingDataAdapter<CaseStudy, CaseStudiesAdaptor.ViewHolder>(
        CaseStudyDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_case_study,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val caseStudy = getItem(position)
        caseStudy?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(
        private val binding: ListItemCaseStudyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.viewModel?.caseStudy?.let { caseStudy ->
                    navigateToDetails(caseStudy, view)
                }
            }
        }

        private fun navigateToDetails(caseStudy: CaseStudy, view: View) {
            val direction = CaseStudiesFragmentDirections.actionHomeFragmentToDetailFragment(caseStudy)
            view.findNavController().navigate(direction)
        }

        fun bind(caseStudy: CaseStudy) {
            with(binding) {
                viewModel = CaseStudiesItemViewModel(caseStudy)
                executePendingBindings()
            }
        }
    }
}

private class CaseStudyDiffCallback : DiffUtil.ItemCallback<CaseStudy>() {

    override fun areItemsTheSame(
        oldItem: CaseStudy,
        newItem: CaseStudy
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CaseStudy,
        newItem: CaseStudy
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
