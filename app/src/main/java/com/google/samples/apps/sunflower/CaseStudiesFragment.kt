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

import androidx.lifecycle.lifecycleScope
import com.google.samples.apps.sunflower.data.CaseStudiesAdaptor
import com.google.samples.apps.sunflower.databinding.FragmentCaseStudiesBinding
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
        viewModel.getCaseStudiesRequest.value = true
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getCaseStudies().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    override fun performUpdateOnViews() {
        super.performUpdateOnViews()
    }
}
