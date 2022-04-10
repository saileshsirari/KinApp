/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.kin.carta.android.casestudies

import androidx.navigation.fragment.navArgs
import com.kin.carta.android.BaseDataFragment
import com.kin.carta.android.R
import com.kin.carta.android.data.SectionAdapter
import com.kin.carta.android.databinding.FragmentCaseStudyDetailsBinding
import com.kin.carta.android.hideProgressDialog
import com.kin.carta.android.showProgressDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CaseStudyDetailsFragment :
    BaseDataFragment<FragmentCaseStudyDetailsBinding, CaseStudyDetailsViewModel>(R.layout.fragment_case_study_details) {
    private val adapter = SectionAdapter()
    private val args: CaseStudyDetailsFragmentArgs? by navArgs()
    override val viewModelClass: Class<CaseStudyDetailsViewModel>
        get() = CaseStudyDetailsViewModel::class.java

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


    private fun initObservers() {
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideProgressDialog()
        }
    }
}
