/*
 * Copyright 2019 Google LLC
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

package com.google.samples.apps.sunflower

import androidx.navigation.fragment.navArgs
import com.google.samples.apps.sunflower.data.SectionAdapter
import com.google.samples.apps.sunflower.databinding.FragmentCaseStudyDetailsBinding
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
