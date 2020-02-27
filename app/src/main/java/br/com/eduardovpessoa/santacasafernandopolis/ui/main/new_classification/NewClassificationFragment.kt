package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_new_classification.*

class NewClassificationFragment : Fragment(), NewClassificationContract.View {

    private var presenter: NewClassificationContract.Presenter? = null
    private lateinit var viewNewClassification: View
    private lateinit var snackbar: Snackbar

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
        viewNewClassification = view
        presenter = NewClassificationPresenter(
            this,
            arguments?.getString("idUnity"),
            arguments?.getString("idBed"),
            arguments?.getString("idClassification")
        )
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
        //btnSave.setOnClickListener { if (validadeClassification()) pushClassification() }
    }

    override fun validadeClassification(): Boolean {
        var msg = ""

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
        if (rgCuidadoCorporal.checkedRadioButtonId == -1) {
            rgCuidadoCorporal.requestFocus()
            msg = "Avalie o Cuidado Corporal!"
        }
        if (rgNutricao.checkedRadioButtonId == -1) {
            rgNutricao.requestFocus()
            msg = "Avalie a Nutrição!"
        }
        if (rgAlimentacao.checkedRadioButtonId == -1) {
            rgAlimentacao.requestFocus()
            msg = "Avalie a Alimentação!"
        }
        if (rgMobilidade.checkedRadioButtonId == -1) {
            rgMobilidade.requestFocus()
            msg = "Avalie a Mobilidade!"
        }
        if (rgDeambulacao.checkedRadioButtonId == -1) {
            rgDeambulacao.requestFocus()
            msg = "Avalie a Deambulação!"
        }
        if (rgSinaisVitais.checkedRadioButtonId == -1) {
            rgSinaisVitais.requestFocus()
            msg = "Avalie os Sinais Vitais!"
        }
        if (rgOxigenacao.checkedRadioButtonId == -1) {
            rgOxigenacao.requestFocus()
            msg = "Avalie a Oxigenação!"
        }
        if (rgEstadoMental.checkedRadioButtonId == -1) {
            rgEstadoMental.requestFocus()
            msg = "Avalie o Estado Mental!"
        }

        if (msg.isNotEmpty())
            showMessage(msg, false)

        return msg.isEmpty()
    }

    override fun pushClassification() {
        presenter?.saveClassification(
            Classification(
                id = arguments?.getString("idUnity") ?: "",
                alimentacao = sumAlimentacao(),
                cuidadoCorporal = sumCuidadoCorporal(),
                curativo = sumCurativo(),
                deambulacao = sumDeambulacao(),
                eliminacao = sumEliminacao(),
                estadoMental = sumEstadoMental(),
                integridade = sumIntegridade(),
                mobilidade = sumMobilidade(),
                oxigenacao = sumOxigenacao(),
                sinaisVitais = sumSinaisVitais(),
                tempoCurativo = sumTempoCurativo(),
                terapeutica = sumTerapeutica(),
                nutricao = sumNutricao(),
                umidade = sumUmidade(),
                totalBraden = calcBraden(),
                totalFugulin = calcFugulin()
            )
        )
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
        snackbar = Snackbar.make(
            viewNewClassification.findViewById(R.id.scrollNewClassfication),
            msg,
            if (infinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    override fun dismissMessage() = snackbar.dismiss()

    override fun setClassification(classification: Classification?) {
        when (classification?.estadoMental) {
            1 -> rgEstadoMental.check(rbEstadoMental1.id)
            2 -> rgEstadoMental.check(rbEstadoMental2.id)
            3 -> rgEstadoMental.check(rbEstadoMental3.id)
            4 -> rgEstadoMental.check(rbEstadoMental4.id)
            else -> rgEstadoMental.check(-1)
        }
        when (classification?.oxigenacao) {
            1 -> rgOxigenacao.check(rbOxigenacao1.id)
            2 -> rgOxigenacao.check(rbOxigenacao2.id)
            3 -> rgOxigenacao.check(rbOxigenacao3.id)
            4 -> rgOxigenacao.check(rbOxigenacao4.id)
            else -> rgOxigenacao.check(-1)
        }
        when (classification?.sinaisVitais) {
            1 -> rgSinaisVitais.check(rbSinaisVitais1.id)
            2 -> rgSinaisVitais.check(rbSinaisVitais2.id)
            3 -> rgSinaisVitais.check(rbSinaisVitais3.id)
            4 -> rgSinaisVitais.check(rbSinaisVitais4.id)
            else -> rgSinaisVitais.check(-1)
        }
        when (classification?.mobilidade) {
            1 -> rgMobilidade.check(rbMobilidade1.id)
            2 -> rgMobilidade.check(rbMobilidade2.id)
            3 -> rgMobilidade.check(rbMobilidade3.id)
            4 -> rgMobilidade.check(rbMobilidade4.id)
            else -> rgMobilidade.check(-1)
        }
        when (classification?.deambulacao) {
            1 -> rgDeambulacao.check(rbDeambulacao1.id)
            2 -> rgDeambulacao.check(rbDeambulacao2.id)
            3 -> rgDeambulacao.check(rbDeambulacao3.id)
            4 -> rgDeambulacao.check(rbDeambulacao4.id)
            else -> rgDeambulacao.check(-1)
        }
        when (classification?.alimentacao) {
            1 -> rgAlimentacao.check(rbAlimentacao1.id)
            2 -> rgAlimentacao.check(rbAlimentacao2.id)
            3 -> rgAlimentacao.check(rbAlimentacao3.id)
            4 -> rgAlimentacao.check(rbAlimentacao4.id)
            else -> rgAlimentacao.check(-1)
        }
        when (classification?.cuidadoCorporal) {
            1 -> rgCuidadoCorporal.check(rbCuidadoCorporal1.id)
            2 -> rgCuidadoCorporal.check(rbCuidadoCorporal2.id)
            3 -> rgCuidadoCorporal.check(rbCuidadoCorporal3.id)
            4 -> rgCuidadoCorporal.check(rbCuidadoCorporal4.id)
            else -> rgCuidadoCorporal.check(-1)
        }
        when (classification?.eliminacao) {
            1 -> rgEliminacao.check(rbEliminacao1.id)
            2 -> rgEliminacao.check(rbEliminacao2.id)
            3 -> rgEliminacao.check(rbEliminacao3.id)
            4 -> rgEliminacao.check(rbEliminacao4.id)
            else -> rgEliminacao.check(-1)
        }
        when (classification?.terapeutica) {
            1 -> rgTerapeutica.check(rbTerapeutica1.id)
            2 -> rgTerapeutica.check(rbTerapeutica2.id)
            3 -> rgTerapeutica.check(rbTerapeutica3.id)
            4 -> rgTerapeutica.check(rbTerapeutica4.id)
            else -> rgTerapeutica.check(-1)
        }
        when (classification?.integridade) {
            1 -> rgIntegridade.check(rbIntegridade1.id)
            2 -> rgIntegridade.check(rbIntegridade2.id)
            3 -> rgIntegridade.check(rbIntegridade3.id)
            4 -> rgIntegridade.check(rbIntegridade4.id)
            else -> rgIntegridade.check(-1)
        }
        when (classification?.curativo) {
            1 -> rgCurativo.check(rbCurativo1.id)
            2 -> rgCurativo.check(rbCurativo2.id)
            3 -> rgCurativo.check(rbCurativo3.id)
            4 -> rgCurativo.check(rbCurativo4.id)
            else -> rgCurativo.check(-1)
        }
        when (classification?.tempoCurativo) {
            1 -> rgTempoCurativo.check(rbTempoCurativo1.id)
            2 -> rgTempoCurativo.check(rbTempoCurativo2.id)
            3 -> rgTempoCurativo.check(rbTempoCurativo3.id)
            4 -> rgTempoCurativo.check(rbTempoCurativo4.id)
            else -> rgTempoCurativo.check(-1)
        }
        when (classification?.nutricao) {
            1 -> rgNutricao.check(rbNutricao1.id)
            2 -> rgNutricao.check(rbNutricao2.id)
            3 -> rgNutricao.check(rbNutricao3.id)
            4 -> rgNutricao.check(rbNutricao4.id)
            else -> rgNutricao.check(-1)
        }
        when (classification?.umidade) {
            1 -> rgUmidade.check(rbUmidade1.id)
            2 -> rgUmidade.check(rbUmidade2.id)
            3 -> rgUmidade.check(rbUmidade3.id)
            4 -> rgUmidade.check(rbUmidade4.id)
            else -> rgUmidade.check(-1)
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

}
