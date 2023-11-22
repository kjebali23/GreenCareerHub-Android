package com.example.gch.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.Models.Certif
import com.example.gch.adapter.CertifAdapter
import com.example.gch.databinding.FragmentCertifBinding
import com.example.gch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CertifFragment: Fragment() {

    lateinit var binding: FragmentCertifBinding;
    private lateinit var certifAdapter: CertifAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCertifBinding.inflate(layoutInflater)



        binding.rvCertif.adapter = CertifAdapter(getListCertif(requireContext()))
        binding.rvCertif.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialiser l'adaptateur avec une liste vide
        certifAdapter = CertifAdapter(mutableListOf()) // Utilisez mutableListOf() pour une liste mutable
        binding.rvCertif.adapter = certifAdapter
        binding.rvCertif.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        Log.e("FRAGMENT Certif", certifAdapter.certifList.toString())

        // Utilisez Retrofit pour récupérer les données depuis MongoDB
        fetchDataFromApi()
    }


    public fun fetchDataFromApi() {
        val api = RetrofitClient.instance
        val call = api.getCertifList()

        call.enqueue(object : Callback<List<Certif>> {
            override fun onResponse(call: Call<List<Certif>>, response: Response<List<Certif>>) {
                if (response.isSuccessful) {
                    val certifList: List<Certif>? = response.body()?.toMutableList()
                    Log.e("FRAGMENT Certif", response.body().toString())
                    // Mettez à jour votre RecyclerView avec les données de MongoDB
                    certifAdapter.updateList(certifList ?: emptyList())
                } else {
                    Log.d("aaaaaaa", (response ?:"Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Certif>>, t: Throwable) {
                // Gérer les erreurs de requête
                Log.e("onFailure", t.toString())
            }
        })
    }




    private fun getListCertif(context: Context) : MutableList<Certif> {
        return mutableListOf(
            Certif(1, "Certif" , "desc" , "url", arrayListOf("Node js")    )
        )
    }


}