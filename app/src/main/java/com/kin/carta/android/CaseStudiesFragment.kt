package com.kin.carta.android

import androidx.lifecycle.lifecycleScope
import com.kin.carta.android.data.CaseStudiesAdaptor
import com.kin.carta.android.databinding.FragmentCaseStudiesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        viewModel.getCaseStudiesRequest.value =true
        viewModel.getCaseStudies.observe(viewLifecycleOwner) {
            it?.let {
                job = lifecycleScope.launch {
                    adapter.submitData(it)
                }
            }
        }
        /*job = lifecycleScope.launch {
            viewModel.getCaseStudies()?.collectLatest {
                adapter.submitData(it)
            }
        }*/
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    override fun performUpdateOnViews() {
        super.performUpdateOnViews()
    }
}
