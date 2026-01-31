package com.example.hab.ui.entry

import androidx.lifecycle.viewModelScope
import com.example.hab.data.mapper.toEntity
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.manual.ManualRecordDraft
import com.example.hab.domain.usecase.GetCategoriesUseCase
import com.example.hab.domain.usecase.InitializeCategoriesUseCase
import com.example.hab.domain.usecase.AddManualRecordUseCase
import com.example.hab.ui.base.BaseViewModel
import com.example.hab.ui.state.UiStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val initializeCategoriesUseCase: InitializeCategoriesUseCase,
    private val addManualRecordUseCase: AddManualRecordUseCase
) : BaseViewModel() {

    override fun getTAG(): String = "RecordViewModel"

    //==============================================================================================
    // Category
    //==============================================================================================
    val categoryState = UiStateHolder<List<Category>>()
    fun fetchCategories() {
        viewModelScope.launch {
            categoryState.run {
                initializeCategoriesUseCase()
                getCategoriesUseCase()
            }
        }
    }

    //==============================================================================================
    // ManualRecord
    //==============================================================================================
    val addManualRecordState = UiStateHolder<Unit>()
    fun addManualRecord(draft: ManualRecordDraft) {
        viewModelScope.launch {
            addManualRecordState.run {
                addManualRecordUseCase(draft)
            }
        }
    }
}