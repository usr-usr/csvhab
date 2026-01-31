package com.example.hab.ui.setting.category_rule

import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.hab.databinding.DialogEditCategoryAssignmentRulesBinding
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.ui.base.BaseDialogFragment
import com.example.hab.ui.util.observeUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCategoryAssignmentRulesDialogFragment :
    BaseDialogFragment<DialogEditCategoryAssignmentRulesBinding>() {

    private val viewModel: CategoryAssignmentRuleViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater
    ): DialogEditCategoryAssignmentRulesBinding {
        return DialogEditCategoryAssignmentRulesBinding.inflate(inflater)
    }

    override fun setupView() {
        observeCategoriesState()
        observeUpdateRuleState()

        binding.closeButton.setOnClickListener { dismiss() }

        binding.okButton.setOnClickListener {
            val updatedRules = collectBulkInput() ?: return@setOnClickListener
            viewModel.updateCategoryAssignmentRules(updatedRules)
        }

        viewModel.fetchCategories()

        setupRadioGroup()
    }

    override fun getTAG(): String = "EditCategoryAssignmentRulesDialogFragment"

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
    private fun setupRadioGroup() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val isKeySelected = checkedId == binding.radioKey.id

            binding.keyContainer.isEnabled = isKeySelected
            binding.keyNoteInput.isEnabled = isKeySelected
            binding.keyContainer.alpha = if (isKeySelected) 1.0f else 0.4f

            val isValueSelected = checkedId == binding.radioValue.id
            binding.valueContainer.isEnabled = isValueSelected
            binding.valueItemInput.isEnabled = isValueSelected
            binding.valueContainer.alpha = if (isValueSelected) 1.0f else 0.4f
        }

        binding.radioKey.isChecked = true
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
    private fun collectBulkInput(): List<CategoryAssignmentRule>? {
        val selectedRules = viewModel.getSelectedRules()
        if (selectedRules.isEmpty()) return null

        return when (binding.radioGroup.checkedRadioButtonId) {
            binding.radioKey.id -> {
                val newKeyword = binding.keyNoteInput.text?.toString()?.trim()
                    ?.takeIf { it.isNotBlank() } ?: return null

                selectedRules.map { rule ->
                    rule.copy(keyword = newKeyword)
                }
            }

            binding.radioValue.id -> {
                val categories = viewModel.categoriesState.current().orEmpty()
                val selectedCategory = categories.getOrNull(binding.valueItemInput.selectedItemPosition)
                    ?: return null

                selectedRules.map { rule ->
                    rule.copy(category = selectedCategory)
                }
            }

            else -> null
        }
    }
}