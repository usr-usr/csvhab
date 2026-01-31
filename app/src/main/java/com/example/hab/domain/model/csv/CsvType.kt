package com.example.hab.domain.model.csv

sealed class CsvType {
    object User : CsvType()
    object PayPay : CsvType()
}