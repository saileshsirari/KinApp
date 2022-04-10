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

import androidx.lifecycle.lifecycleScope
import com.kin.carta.android.BaseDataFragment
import com.kin.carta.android.R
import com.kin.carta.android.data.CaseStudiesAdaptor
import com.kin.carta.android.databinding.FragmentCaseStudiesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
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
        viewModel.getCaseStudiesRequest.value = true
        viewModel.getCaseStudies.observe(viewLifecycleOwner) {
            it?.let {
                job = lifecycleScope.launch {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }
}
