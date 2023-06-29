package com.propertysearchassignment.app.model.data

data class Facility(
    val name: String,
    val options: List<Option>,
    val exclusions: List<Exclusion>
)
