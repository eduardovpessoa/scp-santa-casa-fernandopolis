package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import br.com.eduardovpessoa.santacasafernandopolis.data.util.EmptyAdapter
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.MainAdapterContract
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_classification.*

class ClassificationFragment : Fragment(), ClassificationContract.View {

    private var presenter: ClassificationContract.Presenter? = null
    private var listener: MainAdapterContract.ClassificationAdapter? = null
    private var listenerNew: MainAdapterContract.NewClassificationAdapter? = null
    private lateinit var viewClassification: View
    private lateinit var snackbar: Snackbar

    companion object {
        @JvmStatic
        fun newInstance(idUnity: String?, idBed: String?) = ClassificationFragment().apply {
            arguments = Bundle().apply {
                putString("idUnity", idUnity)
                putString("idBed", idBed)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is MainAdapterContract.ClassificationAdapter -> {
                listener = context
            }
            is MainAdapterContract.NewClassificationAdapter -> {
                listenerNew = context
            }
            else -> {
                Log.e(ClassificationFragment::class.java.name, "onAttach error!")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_classification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerClassification.adapter = EmptyAdapter()
        viewClassification = view
        presenter = ClassificationPresenter(this)
        recyclerClassification.layoutManager = LinearLayoutManager(view.context)
        recyclerClassification.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
        presenter?.loadClassification(
            arguments?.getString("idUnity"),
            arguments?.getString("idBed")
        )
        btnNewClassification.setOnClickListener {
            listenerNew?.onClickNewClassification(
                arguments?.getString("idUnity"),
                arguments?.getString("idBed")
            )
        }
    }

    override fun setAdapter(
        idUnity: String?,
        idBed: String?,
        classificationList: MutableList<Classification>
    ) {
        val adapter = ClassificationAdapter(
            idUnity,
            idBed,
            classificationList,
            object : MainAdapterContract.ClassificationAdapter {
                override fun onClickClassification(
                    idUnity: String?,
                    idBed: String?,
                    idClassification: String?,
                    dateClassification: Long?
                ) {
                    listener?.onClickClassification(
                        idUnity,
                        idBed,
                        idClassification,
                        dateClassification
                    )
                }
            })
        adapter.notifyDataSetChanged()
        recyclerClassification.adapter = adapter
    }

    override fun showMessage(msg: String, infinite: Boolean) {
        snackbar = Snackbar.make(
            viewClassification.findViewById(R.id.recyclerClassification),
            msg,
            if (infinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    override fun dismissMessage() = snackbar.dismiss()

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

}
