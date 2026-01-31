package com.example.hab.ui.setting.category_rule

import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.hab.databinding.DialogAddCategoryAssignmentRuleBinding
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category_rule.CategoryAssignmentRuleDraft
import com.example.hab.ui.base.BaseDialogFragment
import com.example.hab.ui.util.observeUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryAssignmentRuleDialogFragment :
    BaseDialogFragment<DialogAddCategoryAssignmentRuleBinding>() {

    // ViewModel
    private val viewModel: CategoryAssignmentRuleViewModel by activityViewModels()

    //==============================================================================================
    // override
    //==============================================================================================
    override fun createViewBinding(
        inflater: LayoutInflater
    ): DialogAddCategoryAssignmentRuleBinding {
        return DialogAddCategoryAssignmentRuleBinding.inflate(inflater)
    }
    override fun setupView() {

        binding.closeButton.setOnClickListener {
            dismiss()
        }

        observeAddCategoryAssignmentRuleState()
        binding.okButton.setOnClickListener {
            val draft = collectInput() ?: return@setOnClickListener
            viewModel.addCategoryAssignmentRule(draft)
        }

        // setItemList
        observeCategoriesState()
        viewModel.fetchCategories()
    }
    override fun getTAG(): String {
        return "AddCategoryAssignmentRuleDialogFragment"
    }

    //==============================================================================================
    // observer
    //==============================================================================================
    private fun observeCategoriesState() {
        observeUiState(
            stateFlow = viewModel.categoriesState.state,
            onLoading = {
                //
            },
            onSuccess = {
                setupItemDropdown(it)
            },
            onError = {
                //
            }
        )
    }
    private fun observeAddCategoryAssignmentRuleState() {
        observeUiState(
            stateFlow = viewModel.addCategoryAssignmentRuleState.state,
            onLoading = {
                //
            },
            onSuccess = {
                resetInputFields()
                viewModel.addCategoryAssignmentRuleState.clear()
                viewModel.fetchCategoryAssignmentRules()
            },
            onError = {
                //
            }
        )
    }

    //==============================================================================================
    // Ui
    //==============================================================================================
    private fun setupItemDropdown(categories: List<Category>) {

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories
        ).also {
            it.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
        }
        binding.valueItemInput.adapter = adapter
    }
    private fun collectInput(): CategoryAssignmentRuleDraft? {

        val key = binding.keyNoteInput.text
            .toString()
            .trim()
            .takeIf { it.isNotBlank() }
            ?: return null

        val value = binding.valueItemInput.selectedItem as? Category
            ?: return null

        return CategoryAssignmentRuleDraft.create(
            keyword = key,
            category = value
        )
    }
    private fun resetInputFields() {
        binding.keyNoteInput.text?.clear()
    }

}
