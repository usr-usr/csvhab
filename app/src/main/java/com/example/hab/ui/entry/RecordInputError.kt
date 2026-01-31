package com.example.hab.ui.entry

sealed class RecordInputError(val message: String) {
    object IncomeExpenseNotSelected : RecordInputError("収入 / 支出を選択してください")
    object InvalidDate : RecordInputError("年月日を正しく入力してください")
    object ItemNotSelected : RecordInputError("費目を選択してください")
    object InvalidAmount : RecordInputError("金額を正しく入力してください")
}
