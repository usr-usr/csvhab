package com.example.hab.ui.setting.category_rule

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hab.databinding.ActivityEditCategoryAssignmentRuleBinding
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.ui.base.BaseActivity
import com.example.hab.ui.util.observeUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryAssignmentRuleActivity : BaseActivity<ActivityEditCategoryAssignmentRuleBinding>() {

    private val viewModel: CategoryAssignmentRuleViewModel by viewModels()

    private lateinit var adapter: CategoryAssignmentRuleAdapter

    override fun getTAG(): String = "CategoryAssignmentRuleActivity"

    override fun createViewBinding(): ActivityEditCategoryAssignmentRuleBinding =
        ActivityEditCategoryAssignmentRuleBinding.inflate(layoutInflater)

    override fun setupView(savedInstanceState: Bundle?) {
        setupRecyclerView()

        observeCategoriesState()
        observeCategoryAssignmentRulesState()

        // 初期データのロード開始
        viewModel.fetchCategories()

        // 追加ボタン
        binding.addButton.setOnClickListener {
            showAddDialog()
        }

        // 編集ボタン
        binding.editButton.setOnClickListener {
            val selectedRules: List<CategoryAssignmentRule> = viewModel.getSelectedRules()

            when (selectedRules.size) {
                0 -> showToast("項目を選択してください")
                1 -> showEditSingleDialog()
                else -> showEditMultiDialog()
            }
        }
    }

    override fun getAdUnitId(): String = "ca-app-pub-3940256099942544/6300978111"

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
                viewModel.fetchCategoryAssignmentRules()
            },
            onError = {
                //
            }
        )
    }

// CategoryAssignmentRuleActivity.kt

    private fun observeCategoryAssignmentRulesState() {

        observeUiState(
            stateFlow =  viewModel.categoryAssignmentRulesState.state,
            onLoading = {
                //
            },
            onSuccess = {
                viewModel.fetchCategoryAssignmentRuleUiModels()
            },
            onError = {
                //
            }
        )

        observeUiState(
            stateFlow = viewModel.uiModelsState.state,
            onLoading = {
                //
            },
            onSuccess = { uiModels ->
                adapter.submitList(uiModels)
            },
            onError = {
                //
            }
        )
    }

    //==============================================================================================
    // RecyclerView
    //==============================================================================================
    private fun setupRecyclerView() {
        adapter = CategoryAssignmentRuleAdapter(
            onCheckClicked = { ruleId ->
                viewModel.toggleSelection(ruleId)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CategoryAssignmentRuleActivity)
            adapter = this@CategoryAssignmentRuleActivity.adapter
            setHasFixedSize(true)
        }
    }

    //==============================================================================================
    // Dialog
    //==============================================================================================
    private fun showAddDialog() {
        AddCategoryAssignmentRuleDialogFragment()
            .show(supportFragmentManager, "AddCategoryRule")
    }

    private fun showEditSingleDialog() {
        EditCategoryAssignmentRuleDialogFragment()
            .show(supportFragmentManager, "EditSingle")
    }

    private fun showEditMultiDialog() {
        EditCategoryAssignmentRulesDialogFragment()
            .show(supportFragmentManager, "EditMulti")
    }
}