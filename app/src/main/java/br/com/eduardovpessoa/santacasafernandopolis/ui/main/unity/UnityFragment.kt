package br.com.eduardovpessoa.santacasafernandopolis.ui.main.unity


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
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Unity
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.MainAdapterContract
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.MainContract
import kotlinx.android.synthetic.main.fragment_unity.*

class UnityFragment : Fragment(), UnityContract.View {

    private var presenter: UnityContract.Presenter? = null
    private var listener: MainAdapterContract.UnityAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unity, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainAdapterContract.UnityAdapter) {
            listener = context
        } else {
            Log.e("", "deu ruim")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = UnityPresenter(this)
        recyclerUnity.layoutManager = LinearLayoutManager(view.context)
        recyclerUnity.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun setAdapter(unityList: MutableList<Unity>) {
        val adapter = UnityAdapter(
            unityList,
            object : MainAdapterContract.UnityAdapter {
                override fun onClickUnity(id: String?) {
                    listener?.onClickUnity(id)
                }
            })
        adapter.notifyDataSetChanged()
        recyclerUnity.adapter = adapter
    }

    override fun showMessage(msg: String, infinite: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

}
