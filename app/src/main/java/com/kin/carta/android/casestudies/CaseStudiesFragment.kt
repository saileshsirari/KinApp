package com.kin.carta.android.casestudies

import com.kin.carta.android.BaseDataFragment
import com.kin.carta.android.R
import com.kin.carta.android.adapters.CaseStudiesAdaptor
import com.kin.carta.android.databinding.FragmentCaseStudiesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class CaseStudiesFragment :
    BaseDataFragment<FragmentCaseStudiesBinding, CaseStudiesViewModel>(R.layout.fragment_case_studies) {
    private var job: Job? = null
    private val adapter = CaseStudiesAdaptor()

    override val viewModelClass: Class<CaseStudiesViewModel>
        get() = CaseStudiesViewModel::class.java

    override fun performPostBindingOperations() {
        super.performPostBindingOperations()
        viewBinding.caseStudiesRecyclerView.adapter = adapter
        job?.cancel()
        viewModel.getCaseStudiesRequest.value = true
        viewModel.getCaseStudies.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it.caseStudies)
            }
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }
}
