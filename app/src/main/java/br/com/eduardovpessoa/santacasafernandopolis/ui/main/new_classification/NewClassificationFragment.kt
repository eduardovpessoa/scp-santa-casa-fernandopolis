package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.eduardovpessoa.santacasafernandopolis.R

class NewClassificationFragment : Fragment() {

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


}
