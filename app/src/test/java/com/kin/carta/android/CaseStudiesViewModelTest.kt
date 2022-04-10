package com.kin.carta.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.kin.carta.android.domain.CaseStudiesUseCase
import com.kin.carta.android.casestudies.CaseStudiesViewModel
import com.kin.carta.android.data.ICaseStudiesRepository
import com.kin.carta.android.util.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class CaseStudiesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repo: ICaseStudiesRepository

    private lateinit var usecase: CaseStudiesUseCase
    private lateinit var viewModel: CaseStudiesViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        usecase = CaseStudiesUseCase(repo)
        viewModel = CaseStudiesViewModel(usecase)
    }

    @Test
    fun getCaseStudies() {
        mainCoroutineRule.runBlockingTest {
            val mockEntity = TestUtil.getCaseStudies()
           val page =  PagingData.from(mockEntity.caseStudies)
            `when`(usecase.getCaseStudies())
                .thenReturn((flowOf(page)))
            viewModel.getCaseStudiesRequest.value = true

            val v = viewModel.getCaseStudies.getOrAwaitValue(5)

            Assert.assertEquals(mockEntity.caseStudies, v)
        }
    }
}