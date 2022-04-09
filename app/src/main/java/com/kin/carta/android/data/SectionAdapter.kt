package com.kin.carta.android.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kin.carta.android.CaseStudySectionItemViewModel
import com.kin.carta.android.databinding.ListItemCaseStudySectionBinding

class SectionAdapter(
) : ListAdapter<CaseStudySectionItemViewModel, RecyclerView.ViewHolder>(SectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SectionViewHolder(
            ListItemCaseStudySectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bodyElement = getItem(position)
        (holder as SectionViewHolder).bind(bodyElement)
    }

    class SectionViewHolder(
        private val binding: ListItemCaseStudySectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CaseStudySectionItemViewModel) {
            binding.apply {
                viewModel = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private const val TYPE_SECTION_HEADER = 0
        private const val TYPE_BODY_TEXT = 1
        private const val TYPE_BODY_IMAGE = 2
    }
}

private class SectionDiffCallback : DiffUtil.ItemCallback<CaseStudySectionItemViewModel>() {

    override fun areItemsTheSame(
        oldItem: CaseStudySectionItemViewModel,
        newItem: CaseStudySectionItemViewModel
    ): Boolean {
        return oldItem.sectionTitle == newItem.sectionTitle
    }

    override fun areContentsTheSame(
        oldItem: CaseStudySectionItemViewModel,
        newItem: CaseStudySectionItemViewModel
    ): Boolean {
        return oldItem.sectionTitle == newItem.sectionTitle
    }
}