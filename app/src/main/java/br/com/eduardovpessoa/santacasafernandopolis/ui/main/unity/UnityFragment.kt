package br.com.eduardovpessoa.santacasafernandopolis.ui.main.unity


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Unity
import kotlinx.android.synthetic.main.fragment_unity.*

class UnityFragment : Fragment(), UnityContract.View {

    private var presenter: UnityContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unity, container, false)
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
        val adapter = UnityAdapter(unityList)
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
