
package com.kin.carta.android

import androidx.navigation.fragment.navArgs
import com.kin.carta.android.data.SectionAdapter
import com.kin.carta.android.databinding.FragmentCaseStudyDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CaseStudyDetailsFragment :
    BaseDataFragment<FragmentCaseStudyDetailsBinding, CaseStudyDetailViewModel>(R.layout.fragment_case_study_details) {
    private val adapter = SectionAdapter()
    private val args: CaseStudyDetailsFragmentArgs? by navArgs()
    override val viewModelClass: Class<CaseStudyDetailViewModel>
        get() = CaseStudyDetailViewModel::class.java

    override fun performPreBindingOperations() {
        super.performPreBindingOperations()
        args?.caseStudy?.let { caseStudy ->
            viewModel.caseStudy.value = caseStudy
        }
    }

    override fun performPostBindingOperations() {
        super.performPostBindingOperations()
        initObservers()
        viewBinding.caseStudyDetailRecyclerView.adapter = adapter
        viewBinding.executePendingBindings()
        args?.caseStudy?.let { caseStudy ->
            showProgressDialog()
            viewModel.initItems(caseStudy)
        }
    }

    override fun performUpdateOnViews() {
        super.performUpdateOnViews()
    }

    private fun initObservers() {
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideProgressDialog()
        }
    }
}
