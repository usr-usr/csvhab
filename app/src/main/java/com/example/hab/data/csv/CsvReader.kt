package com.example.hab.data.csv

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
//
import com.example.hab.domain.model.csv.CsvHeader
import com.example.hab.domain.model.csv.CsvRow
import com.example.hab.domain.model.csv.CsvTable

class CsvReader(
    private val context: Context,
    private val charset: Charset = Charsets.UTF_8
) {
    private val TAG = "CsvReader"

    suspend fun read(uri: Uri): CsvReadResult =
        withContext(Dispatchers.IO) {
            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream, charset)).useLines { lines ->

                        val iterator = lines.iterator()

                        // ヘッダ行が存在しない
                        if (!iterator.hasNext()) {
                            return@withContext CsvReadResult.Error("CSVが空です")
                        }

                        // ヘッダ読み込み
                        val headerLine = iterator.next()
                        val headerColumns = headerLine.split(",")
                            .map { it.trim() }

                        val header = CsvHeader(headerColumns)

                        // データ行読み込み
                        val rows = mutableListOf<CsvRow>()
                        iterator.forEachRemaining { line ->
                            // 空行はスキップ
                            if (line.isBlank()) return@forEachRemaining

                            val values = line.split(",")
                            rows.add(CsvRow(values))
                        }

                        CsvReadResult.Success(
                            CsvTable(
                                header = header,
                                rows = rows
                            )
                        )
                    }
                } ?: CsvReadResult.Error("ファイルを開けませんでした")

            } catch (e: Exception) {
                CsvReadResult.Error("CSV読み込みに失敗しました: ${e.message}")
            }
        }
}
