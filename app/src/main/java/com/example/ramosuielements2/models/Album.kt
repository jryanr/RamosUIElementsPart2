package com.example.ramosuielements2.models

class Album (var id: Int = 0, var title: String, var release_date: String){
    override fun toString(): String {
        return "Title: ${title}, Album: ${release_date}"
    }
}