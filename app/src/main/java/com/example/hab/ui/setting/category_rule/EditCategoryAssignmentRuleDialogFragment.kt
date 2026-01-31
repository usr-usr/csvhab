package com.example.hab.ui.setting.category_rule

import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.hab.databinding.DialogEditCategoryAssignmentRuleBinding
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.ui.base.BaseDialogFragment
import com.example.hab.ui.util.observeUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCategoryAssignmentRuleDialogFragment :
    BaseDialogFragment<DialogEditCategoryAssignmentRuleBinding>() {

    private val viewModel: CategoryAssignmentRuleViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater
    ): DialogEditCategoryAssignmentRuleBinding {
        return DialogEditCategoryAssignmentRuleBinding.inflate(inflater)
    }

    override fun setupView() {
        // observer
        observeCategoriesState()
        observeUpdateRuleState()

        binding.closeButton.setOnClickListener {
            dismiss()
        }

        binding.okButton.setOnClickListener {
            val updatedRule = collectInput() ?: return@setOnClickListener
            viewModel.updateCategoryAssignmentRule(updatedRule)
        }

        viewModel.fetchCategories()
    }

    override fun getTAG(): String = "EditCategoryAssignmentRuleDialogFragment"

    //==============================================================================================
    // observer
    //==============================================================================================
    private fun observeCategoriesState() {
        observeUiState(
            stateFlow = viewModel.categoriesState.state,
            onLoading = {
                //
            },
            onSuccess = { categories ->
                setupCategoryDropdown(categories)
                initializeInputFields()
            },
            onError = {
                //
            }
        )
    }
    private fun observeUpdateRuleState() {
        observeUiState(
            stateFlow = viewModel.updateCategoryAssignmentRuleState.state,
            onLoading = {
                //
            },
            onSuccess = {
                viewModel.fetchCategoryAssignmentRules()
                viewModel.updateCategoryAssignmentRuleState.clear()
                dismiss()
            },
            onError = {
                //
            }
        )
    }

    //==============================================================================================
    // Ui
    //==============================================================================================
    private fun initializeInputFields() {
        val selectedRule = viewModel.getSelectedRules().firstOrNull() ?: return

        binding.keyNoteInput.setText(selectedRule.keyword)

        val categories = viewModel.categoriesState.current().orEmpty()
        val index = categories.indexOfFirst { it.id == selectedRule.category.id }
        if (index >= 0) {
            binding.valueItemInput.setSelection(index)
        }
    }
    private fun setupCategoryDropdown(categories: List<Category>) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories.map { it.name }
        ).also {
            it.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
        }

        binding.valueItemInput.adapter = adapter
    }
    private fun collectInput(): CategoryAssignmentRule? {
        val originalRule = viewModel.getSelectedRules().firstOrNull() ?: return null

        val keyword = binding.keyNoteInput.text?.toString()?.trim()
            ?.takeIf { it.isNotBlank() } ?: return null

        val categories = viewModel.categoriesState.current().orEmpty()
        val selectedCategory = categories.getOrNull(binding.valueItemInput.selectedItemPosition) ?: return null

        return CategoryAssignmentRule.create(
            id = originalRule.id,
            keyword = keyword,
            category = selectedCategory
        )
    }
}