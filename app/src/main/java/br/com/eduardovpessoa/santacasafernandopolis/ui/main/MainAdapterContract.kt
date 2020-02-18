package br.com.eduardovpessoa.santacasafernandopolis.ui.main

interface MainAdapterContract {
    interface BedAdapter {
        fun onClickBed(idUnity: String?, idBed: String?, nameBed: String?)
    }

    interface ClassificationAdapter {
        fun onClickClassification(
            idUnity: String?,
            idBed: String?,
            idClassification: String?,
            dateClassification: Long?
        )
    }

    interface NewClassificationAdapter {
        fun onClickNewClassification(
            idUnity: String?,
            idBed: String?,
            idClassification: String?,
            dateClassification: Long?
        )
    }

    interface UnityAdapter {
        fun onClickUnity(idUnity: String?, nameUnity: String?)
    }
}