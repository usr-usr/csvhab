package com.example.hab.ui.setting.category_rule

import androidx.lifecycle.viewModelScope
import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.domain.model.category_rule.CategoryAssignmentRuleDraft
import com.example.hab.domain.usecase.GetCategoriesUseCase
import com.example.hab.domain.usecase.InitializeCategoriesUseCase
import com.example.hab.domain.usecase.AddCategoryAssignmentRuleUseCase
import com.example.hab.domain.usecase.GetCategoryAssignmentRulesUseCase
import com.example.hab.domain.usecase.UpdateCategoryAssignmentRuleUseCase
import com.example.hab.domain.usecase.UpdateCategoryAssignmentRulesUseCase
import com.example.hab.ui.base.BaseViewModel
import com.example.hab.ui.state.UiStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryAssignmentRuleViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val initializeCategoriesUseCase: InitializeCategoriesUseCase,
    private val addCategoryAssignmentRuleUseCase: AddCategoryAssignmentRuleUseCase,
    private val getCategoryAssignmentRulesUseCase: GetCategoryAssignmentRulesUseCase,
    private val updateCategoryAssignmentRuleUseCase: UpdateCategoryAssignmentRuleUseCase,
    private val updateCategoryAssignmentRulesUseCase: UpdateCategoryAssignmentRulesUseCase
) : BaseViewModel() {

    override fun getTAG(): String = "SettingsViewModel"

    //==============================================================================================
    // Category
    //==============================================================================================
    val categoriesState = UiStateHolder<List<Category>>()
    fun fetchCategories() {
        viewModelScope.launch {
            categoriesState.run {
                initializeCategoriesUseCase()
                getCategoriesUseCase()
            }
        }
    }

    //==============================================================================================
    // CategoryAssignmentRule
    //==============================================================================================

    //----------------------------------------------------------------------------------------------
    // Fetch
    //----------------------------------------------------------------------------------------------
    val categoryAssignmentRulesState = UiStateHolder<List<CategoryAssignmentRule>>()
    fun fetchCategoryAssignmentRules() {
        viewModelScope.launch {
            categoryAssignmentRulesState.run {
                getCategoryAssignmentRulesUseCase()
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // Add
    //----------------------------------------------------------------------------------------------
    val addCategoryAssignmentRuleState = UiStateHolder<Unit>()
    fun addCategoryAssignmentRule(draft: CategoryAssignmentRuleDraft) {
        viewModelScope.launch {
            addCategoryAssignmentRuleState.run {
                addCategoryAssignmentRuleUseCase(draft)
                fetchCategoryAssignmentRules()
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // Update
    //----------------------------------------------------------------------------------------------
    val updateCategoryAssignmentRuleState = UiStateHolder<Unit>()
    fun updateCategoryAssignmentRule(rule: CategoryAssignmentRule) {
        viewModelScope.launch {
            updateCategoryAssignmentRuleState.run {
                updateCategoryAssignmentRuleUseCase(rule)
                fetchCategoryAssignmentRules()
            }
        }
    }
    fun updateCategoryAssignmentRules(rules: List<CategoryAssignmentRule>) {
        viewModelScope.launch {
            updateCategoryAssignmentRuleState.run {
                updateCategoryAssignmentRulesUseCase(rules)
                fetchCategoryAssignmentRules()
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    //
    //----------------------------------------------------------------------------------------------
    val uiModelsState = UiStateHolder<List<CategoryAssignmentRuleUiModel>>()
    fun fetchCategoryAssignmentRuleUiModels() {
        viewModelScope.launch {

            val domainRules = categoryAssignmentRulesState.current().orEmpty()

            val uiModels = domainRules.map {
                CategoryAssignmentRuleUiModel(it)
            }

            uiModelsState.setSuccess(uiModels)
        }
    }
    fun toggleSelection(id: Id) {
        val currentList = uiModelsState.current() ?: return
        val newList = currentList.map { uiModel ->
            if (uiModel.id == id) {
                uiModel.copy(isSelected = !uiModel.isSelected)
            } else {
                uiModel
            }
        }
        uiModelsState.setSuccess(newList)
    }

    fun getSelectedRules(): List<CategoryAssignmentRule> {
        return uiModelsState.current()
            ?.filter { it.isSelected }
            ?.map { it.rule } ?: emptyList()
    }

}