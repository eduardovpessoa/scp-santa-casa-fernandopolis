package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup

import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import kotlinx.android.synthetic.main.fragment_new_classification.*

class NewClassificationFragment : Fragment(), NewClassificationContract.View {

    private var presenter: NewClassificationContract.Presenter? = null

    companion object {
        @JvmStatic
        fun newInstance(idUnity: String?, idBed: String?, idClassification: String) =
            NewClassificationFragment().apply {
                arguments = Bundle().apply {
                    putString("idUnity", idUnity)
                    putString("idBed", idBed)
                    putString("idClassification", idClassification)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_classification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initViews() {
        rgEstadoMental.setOnCheckedChangeListener { _, _ ->
            calcFugulin()
            calcBraden()
        }
        rgOxigenacao.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        rgSinaisVitais.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        rgDeambulacao.setOnCheckedChangeListener { _, _ ->
            calcFugulin()
            calcBraden()
        }
        rgMobilidade.setOnCheckedChangeListener { _, _ ->
            calcFugulin()
            calcBraden()
        }
        rgAlimentacao.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        rgNutricao.setOnCheckedChangeListener { _, _ -> calcBraden() }
        rgCuidadoCorporal.setOnCheckedChangeListener { _, _ ->
            calcFugulin()
            calcBraden()
        }
        rgIntegridade.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        rgCurativo.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        rgTempoCurativo.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        rgUmidade.setOnCheckedChangeListener { _, _ -> calcBraden() }
        rgEliminacao.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        rgTerapeutica.setOnCheckedChangeListener { _, _ -> calcFugulin() }
        btnSave.setOnClickListener { validadeClassification() }
    }

    override fun validadeClassification() {
        var msg: String = ""

        if (rgTerapeutica.checkedRadioButtonId == -1) {
            rgTerapeutica.requestFocus()
            msg = "Avalie a Terapêutica!"
        }
        if (rgEliminacao.checkedRadioButtonId == -1) {
            rgEliminacao.requestFocus()
            msg = "Avalie a Eliminação!"
        }
        if (rgUmidade.checkedRadioButtonId == -1) {
            rgUmidade.requestFocus()
            msg = "Avalie a Umidade!"
        }
        if (rgTempoCurativo.checkedRadioButtonId == -1) {
            rgTempoCurativo.requestFocus()
            msg = "Avalie o Tempo do Curativo!"
        }
        if (rgCurativo.checkedRadioButtonId == -1) {
            rgCurativo.requestFocus()
            msg = "Avalie o Curativo!"
        }



        if (rgIntegridade.checkedRadioButtonId == -1) {
            rgIntegridade.requestFocus()
            msg = "Avalie a Integridade!"
        }


        if (rgEstadoMental.checkedRadioButtonId == -1) {
            rgEstadoMental.requestFocus()
            msg = "Avalie o Estado Mental!"
        }
        if (rgOxigenacao.checkedRadioButtonId == -1) {
            rgOxigenacao.requestFocus()
            msg = "Avalie a Oxigenação!"
        }
        if (rgSinaisVitais.checkedRadioButtonId == -1) {
            rgSinaisVitais.requestFocus()
            msg = "Avalie os Sinais Vitais!"
        }
        if (rgDeambulacao.checkedRadioButtonId == -1) {
            rgDeambulacao.requestFocus()
            msg = "Avalie a Deambulação!"
        }
        if (rgMobilidade.checkedRadioButtonId == -1) {
            rgMobilidade.requestFocus()
            msg = "Avalie a Mobilidade!"
        }
        if (rgAlimentacao.checkedRadioButtonId == -1) {
            rgAlimentacao.requestFocus()
            msg = "Avalie a Alimentação!"
        }
        if (rgNutricao.checkedRadioButtonId == -1) {
            rgNutricao.requestFocus()
            msg = "Avalie a Nutrição!"
        }
        if (rgCuidadoCorporal.checkedRadioButtonId == -1) {
            rgCuidadoCorporal.requestFocus()
            msg = "Avalie o Cuidado Corporal!"
        }

        presenter?.saveClassification(Classification())
    }


    override fun calcBraden(): Int {
        var totalBraden = 0
        totalBraden += sumCuidadoCorporalBraden()
        totalBraden += sumDeambulacaoBraden()
        totalBraden += sumEstadoMentalBraden()
        totalBraden += sumMobilidadeBraden()
        totalBraden += sumNutricao()
        totalBraden += sumUmidade()
        txtTotalBraden.text = totalBraden.toString()
        return totalBraden
    }

    override fun calcFugulin(): Int {
        var totalFugulin = 0
        totalFugulin += sumEstadoMental()
        totalFugulin += sumOxigenacao()
        totalFugulin += sumSinaisVitais()
        totalFugulin += sumMobilidade()
        totalFugulin += sumDeambulacao()
        totalFugulin += sumAlimentacao()
        totalFugulin += sumCuidadoCorporal()
        totalFugulin += sumEliminacao()
        totalFugulin += sumTerapeutica()
        totalFugulin += sumIntegridade()
        totalFugulin += sumCurativo()
        totalFugulin += sumTempoCurativo()
        txtTotalFugulin.text = totalFugulin.toString()
        return totalFugulin
    }

    override fun sumEstadoMental(): Int {
        return when (rgEstadoMental.checkedRadioButtonId) {
            R.id.rbEstadoMental4 -> 4
            R.id.rbEstadoMental3 -> 3
            R.id.rbEstadoMental2 -> 2
            R.id.rbEstadoMental1 -> 1
            else -> 0
        }
    }

    override fun sumEstadoMentalBraden(): Int {
        return when (rgEstadoMental.checkedRadioButtonId) {
            R.id.rbEstadoMental4 -> 1
            R.id.rbEstadoMental3 -> 2
            R.id.rbEstadoMental2 -> 3
            R.id.rbEstadoMental1 -> 4
            else -> 0
        }
    }

    override fun sumOxigenacao(): Int {
        return when (rgOxigenacao.checkedRadioButtonId) {
            R.id.rbOxigenacao4 -> 4
            R.id.rbOxigenacao3 -> 3
            R.id.rbOxigenacao2 -> 2
            R.id.rbOxigenacao1 -> 1
            else -> 0
        }
    }

    override fun sumSinaisVitais(): Int {
        return when (rgSinaisVitais.checkedRadioButtonId) {
            R.id.rbSinaisVitais4 -> 4
            R.id.rbSinaisVitais3 -> 3
            R.id.rbSinaisVitais2 -> 2
            R.id.rbSinaisVitais1 -> 1
            else -> 0
        }
    }

    override fun sumMobilidade(): Int {
        return when (rgMobilidade.checkedRadioButtonId) {
            R.id.rbMobilidade4 -> 4
            R.id.rbMobilidade3 -> 3
            R.id.rbMobilidade2 -> 2
            R.id.rbMobilidade1 -> 1
            else -> 0
        }
    }

    override fun sumMobilidadeBraden(): Int {
        return when (rgMobilidade.checkedRadioButtonId) {
            R.id.rbMobilidade4 -> 1
            R.id.rbMobilidade3 -> 2
            R.id.rbMobilidade2 -> 3
            R.id.rbMobilidade1 -> 4
            else -> 0
        }
    }

    override fun sumDeambulacao(): Int {
        return when (rgDeambulacao.checkedRadioButtonId) {
            R.id.rbDeambulacao4 -> 4
            R.id.rbDeambulacao3 -> 3
            R.id.rbDeambulacao2 -> 2
            R.id.rbDeambulacao1 -> 1
            else -> 0
        }
    }

    override fun sumDeambulacaoBraden(): Int {
        return when (rgDeambulacao.checkedRadioButtonId) {
            R.id.rbDeambulacao4 -> 4
            R.id.rbDeambulacao3 -> 3
            R.id.rbDeambulacao2 -> 2
            R.id.rbDeambulacao1 -> 1
            else -> 0
        }
    }

    override fun sumAlimentacao(): Int {
        return when (rgAlimentacao.checkedRadioButtonId) {
            R.id.rbAlimentacao4 -> 4
            R.id.rbAlimentacao3 -> 3
            R.id.rbAlimentacao2 -> 2
            R.id.rbAlimentacao1 -> 1
            else -> 0
        }
    }

    override fun sumCuidadoCorporal(): Int {
        return when (rgCuidadoCorporal.checkedRadioButtonId) {
            R.id.rbCuidadoCorporal4 -> 4
            R.id.rbCuidadoCorporal3 -> 3
            R.id.rbCuidadoCorporal2 -> 2
            R.id.rbCuidadoCorporal1 -> 1
            else -> 0
        }
    }

    override fun sumCuidadoCorporalBraden(): Int {
        return when (rgCuidadoCorporal.checkedRadioButtonId) {
            R.id.rbCuidadoCorporal4 -> 1
            R.id.rbCuidadoCorporal3 -> 2
            R.id.rbCuidadoCorporal2 -> 2
            R.id.rbCuidadoCorporal1 -> 3
            else -> 0
        }
    }

    override fun sumEliminacao(): Int {
        return when (rgEliminacao.checkedRadioButtonId) {
            R.id.rbEliminacao4 -> 4
            R.id.rbEliminacao3 -> 3
            R.id.rbEliminacao2 -> 2
            R.id.rbEliminacao1 -> 1
            else -> 0
        }
    }

    override fun sumTerapeutica(): Int {
        return when (rgTerapeutica.checkedRadioButtonId) {
            R.id.rbTerapeutica4 -> 4
            R.id.rbTerapeutica3 -> 3
            R.id.rbTerapeutica2 -> 2
            R.id.rbTerapeutica1 -> 1
            else -> 0
        }
    }

    override fun sumIntegridade(): Int {
        return when (rgIntegridade.checkedRadioButtonId) {
            R.id.rbIntegridade4 -> 4
            R.id.rbIntegridade3 -> 3
            R.id.rbIntegridade2 -> 2
            R.id.rbIntegridade1 -> 1
            else -> 0
        }
    }

    override fun sumCurativo(): Int {
        return when (rgCurativo.checkedRadioButtonId) {
            R.id.rbCurativo4 -> 4
            R.id.rbCurativo3 -> 3
            R.id.rbCurativo2 -> 2
            R.id.rbCurativo1 -> 1
            else -> 0
        }
    }

    override fun sumTempoCurativo(): Int {
        return when (rgTempoCurativo.checkedRadioButtonId) {
            R.id.rbTempoCurativo4 -> 4
            R.id.rbTempoCurativo3 -> 3
            R.id.rbTempoCurativo2 -> 2
            R.id.rbTempoCurativo1 -> 1
            else -> 0
        }
    }

    override fun sumNutricao(): Int {
        return when (rgNutricao.checkedRadioButtonId) {
            R.id.rbNutricao4 -> 4
            R.id.rbNutricao3 -> 3
            R.id.rbNutricao2 -> 2
            R.id.rbNutricao1 -> 1
            else -> 0
        }
    }

    override fun sumUmidade(): Int {
        return when (rgUmidade.checkedRadioButtonId) {
            R.id.rbUmidade4 -> 4
            R.id.rbUmidade3 -> 3
            R.id.rbUmidade2 -> 2
            R.id.rbUmidade1 -> 1
            else -> 0
        }
    }


    override fun showMessage(msg: String, infinite: Boolean) {

    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

}
