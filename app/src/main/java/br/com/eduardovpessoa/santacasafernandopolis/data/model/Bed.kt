package br.com.eduardovpessoa.santacasafernandopolis.data.model

import com.google.firebase.firestore.DocumentId

data class Bed(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val status: Boolean = false
)