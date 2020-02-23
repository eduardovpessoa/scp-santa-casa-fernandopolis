package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification

import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification

interface NewClassificationContract {
    interface View {
        fun initViews()
        fun validadeClassification()
        fun calcBraden(): Int
        fun calcFugulin(): Int
        fun sumEstadoMental(): Int
        fun sumEstadoMentalBraden(): Int
        fun sumOxigenacao(): Int
        fun sumSinaisVitais(): Int
        fun sumMobilidade(): Int
        fun sumMobilidadeBraden(): Int
        fun sumDeambulacao(): Int
        fun sumDeambulacaoBraden(): Int
        fun sumAlimentacao(): Int
        fun sumCuidadoCorporal(): Int
        fun sumCuidadoCorporalBraden(): Int
        fun sumEliminacao(): Int
        fun sumTerapeutica(): Int
        fun sumIntegridade(): Int
        fun sumCurativo(): Int
        fun sumTempoCurativo(): Int
        fun sumNutricao(): Int
        fun sumUmidade(): Int
        fun showMessage(msg: String, infinite: Boolean)
        fun onDestroy()
    }

    interface Presenter {
        fun loadClassification(idUnity: String?, idBed: String?, idClassification: String?)
        fun saveClassification(classification: Classification?)
        fun onDestroy()
    }
}