package br.com.eduardovpessoa.santacasafernandopolis.ui.main.unity


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
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Unity
import br.com.eduardovpessoa.santacasafernandopolis.data.util.EmptyAdapter
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.MainAdapterContract
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_unity.*

class UnityFragment : Fragment(), UnityContract.View {

    private var presenter: UnityContract.Presenter? = null
    private var listener: MainAdapterContract.UnityAdapter? = null
    private lateinit var viewUnity: View
    private lateinit var snackbar: Snackbar

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
            Log.e(UnityFragment::class.java.name, "onAttach error!")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerUnity.adapter = EmptyAdapter()
        viewUnity = view
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
                override fun onClickUnity(idUnity: String?, nameUnity: String?) {
                    listener?.onClickUnity(idUnity, nameUnity)
                }
            })
        adapter.notifyDataSetChanged()
        recyclerUnity.adapter = adapter
    }

    override fun showMessage(msg: String, infinite: Boolean) {
        snackbar = Snackbar.make(
            viewUnity.findViewById(R.id.recyclerUnity),
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
