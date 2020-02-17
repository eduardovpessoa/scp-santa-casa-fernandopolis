package br.com.eduardovpessoa.santacasafernandopolis.ui.main.bed


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Bed
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.MainAdapterContract
import kotlinx.android.synthetic.main.fragment_bed.*

class BedFragment : Fragment(), BedContract.View {

    private var presenter: BedContract.Presenter? = null
    private var listener: MainAdapterContract.BedAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(idUnity: String?, nameUnity: String?) = BedFragment().apply {
            arguments = Bundle().apply {
                putString("idUnity", idUnity)
                putString("nameUnity", nameUnity)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainAdapterContract.BedAdapter) {
            listener = context
        } else {
            Log.e("", "deu ruim")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = BedPresenter(this)
        recyclerBed.layoutManager = LinearLayoutManager(view.context)
        recyclerBed.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
        presenter?.loadBed(arguments?.getString("idUnity"))
    }

    override fun setAdapter(idUnity: String?, bedList: MutableList<Bed>) {
        val adapter = BedAdapter(
            idUnity,
            bedList,
            object : MainAdapterContract.BedAdapter {
                override fun onClickBed(idUnity: String?, idBed: String?, nameBed: String?) {
                    listener?.onClickBed(idUnity, idBed, nameBed)
                }
            })
        adapter.notifyDataSetChanged()
        recyclerBed.adapter = adapter
    }

    override fun showMessage(msg: String, infinite: Boolean) {

    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

}
