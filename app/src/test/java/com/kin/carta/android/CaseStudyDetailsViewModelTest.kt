package com.kin.carta.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.kin.carta.android.data.CaseStudiesUseCase
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
class CaseStudyDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: CaseStudyDetailsViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = CaseStudyDetailsViewModel()
    }

    @Test
    fun getCaseStudy() {
        mainCoroutineRule.runBlockingTest {
            val mockEntity = TestUtil.getCaseStudy()
            viewModel.initItems(mockEntity)
            val v = viewModel.items.getOrAwaitValue()
            Assert.assertNotNull(v)
            var sizeOfBodyElements = 0
            mockEntity.sections.forEach {
                sizeOfBodyElements =sizeOfBodyElements.plus(1)
                sizeOfBodyElements =sizeOfBodyElements.plus(it.bodyElements.size)
            }
            Assert.assertEquals(sizeOfBodyElements,v.size)
        }
    }
}