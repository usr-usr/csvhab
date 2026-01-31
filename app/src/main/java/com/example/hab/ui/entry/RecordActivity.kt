package com.example.hab.ui.entry

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.hab.databinding.ActivityEntryBinding
import com.example.hab.domain.model.Date
import com.example.hab.domain.model.Money
import com.example.hab.domain.model.TransactionType
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.manual.ManualRecordDraft
import com.example.hab.ui.base.BaseActivity
import com.example.hab.ui.util.observeUiState
import com.google.android.gms.ads.AdRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordActivity : BaseActivity<ActivityEntryBinding>() {

    private val viewModel: RecordViewModel by viewModels()

    override fun getTAG(): String = "RecordActivity"

    override fun createViewBinding(): ActivityEntryBinding =
        ActivityEntryBinding.inflate(layoutInflater)

    override fun setupView(savedInstanceState: Bundle?) {
        // button
        binding.addButton.setOnClickListener {
            val draft = collectInput() ?: return@setOnClickListener
            viewModel.addManualRecord(draft)
        }

        // ad
        val adRequest = AdRequest.Builder().build()
        binding.adTopBanner.loadAd(adRequest)
    }

    override fun getAdUnitId(): String = "ca-app-pub-3940256099942544/6300978111"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeCategoryState()
        observeAddManualRecordState()

        viewModel.fetchCategories()
    }

    //==============================================================================================
    // observer
    //==============================================================================================
    private fun observeCategoryState() {
        observeUiState(
            stateFlow = viewModel.categoryState.state,
            onLoading = {
                //
            },
            onSuccess = {
                categories -> setupCategoryDropdown(categories)
            },
            onError = {
                //
            }
        )
    }
    private fun observeAddManualRecordState() {
        observeUiState(
            stateFlow = viewModel.addManualRecordState.state,
            onLoading = {
                binding.addButton.isEnabled = false
            },
            onSuccess = {
                binding.addButton.isEnabled = true
                resetInputFields()
            },
            onError = {
                binding.addButton.isEnabled = true
            }
        )
    }

    //==============================================================================================
    // Category Spinner
    //==============================================================================================
    private fun setupCategoryDropdown(categories: List<Category>) {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categories
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.itemInput.adapter = adapter
    }

    //==============================================================================================
    // ManualRecord
    //==============================================================================================
    private fun collectInput(): ManualRecordDraft? {
        val type = when {
            binding.radioIncome.isChecked -> TransactionType.INCOME
            binding.radioExpense.isChecked -> TransactionType.EXPENSE
            else -> {
                showToast(RecordInputError.IncomeExpenseNotSelected.message)
                return null
            }
        }

        val year = binding.yearInput.text.toString().toIntOrNull()
        val month = binding.monthInput.text.toString().toIntOrNull()
        val day = binding.dayInput.text.toString().toIntOrNull()
        val amountValue = binding.costInput.text.toString().toIntOrNull()

        if (year == null || month == null || day == null) {
            showToast(RecordInputError.InvalidDate.message)
            return null
        }

        if (amountValue == null) {
            showToast(RecordInputError.InvalidAmount.message)
            return null
        }

        val category = binding.itemInput.selectedItem as? Category ?: run {
            showToast(RecordInputError.ItemNotSelected.message)
            return null
        }

        return ManualRecordDraft.create(
            type = type,
            date = Date.create(year, month, day),
            category = category,
            amount = Money.create(amountValue),
            note = binding.noteInput.text.toString().takeIf { it.isNotBlank() }
        )
    }

    //==============================================================================================
    // Ui
    //==============================================================================================
    private fun resetInputFields() {
        binding.radioIncome.isChecked = false
        binding.radioExpense.isChecked = false
        binding.yearInput.text?.clear()
        binding.monthInput.text?.clear()
        binding.dayInput.text?.clear()
        binding.itemInput.setSelection(0)
        binding.costInput.text?.clear()
        binding.noteInput.text?.clear()
    }
}