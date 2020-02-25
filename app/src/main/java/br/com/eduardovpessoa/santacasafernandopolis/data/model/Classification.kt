package br.com.eduardovpessoa.santacasafernandopolis.data.model

import com.google.firebase.firestore.DocumentId
import java.text.SimpleDateFormat
import java.util.*

data class Classification(
    @DocumentId
    val id: String = "",
    val alimentacao: Int = 0,
    val cuidadoCorporal: Int = 0,
    val curativo: Int = 0,
    val deambulacao: Int = 0,
    val eliminacao: Int = 0,
    val estadoMental: Int = 0,
    val integridade: Int = 0,
    val mobilidade: Int = 0,
    val oxigenacao: Int = 0,
    val registered: Date = Date(),
    val sinaisVitais: Int = 0,
    val status: Boolean = true,
    val tempoCurativo: Int = 0,
    val terapeutica: Int = 0,
    val totalBraden: Int = 0,
    val totalFugulin: Int = 0
) {
    override fun toString(): String {
        return "Data: ${SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "BR")
        ).format(registered)} - ${if (id.isEmpty()) "Nova Class." else "Pontos: F$totalFugulin - B$totalBraden"}"
    }
}