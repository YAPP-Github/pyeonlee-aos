package com.peonlee.feature.mymodel.ui

import com.peonlee.core.data.MyModelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class MyModelViewModelTest {
    @Test
    fun uiState_initiallyLoading() = runTest {
        val viewModel = MyModelViewModel(FakeMyModelRepository())
        assertEquals(viewModel.uiState.first(), MyModelUiState.Loading)
    }

    @Test
    fun uiState_onItemSaved_isDisplayed() = runTest {
        val viewModel = MyModelViewModel(FakeMyModelRepository())
        assertEquals(viewModel.uiState.first(), MyModelUiState.Loading)
    }
}

private class FakeMyModelRepository : MyModelRepository {

    private val data = mutableListOf<String>()

    override val myModels: Flow<List<String>>
        get() = flow { emit(data.toList()) }

    override suspend fun add(name: String) {
        data.add(0, name)
    }
}
