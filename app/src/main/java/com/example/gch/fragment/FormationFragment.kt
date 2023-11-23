package com.example.gch.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gch.databinding.FragmentFormationBinding

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.Models.Formation
import com.example.gch.R
import com.example.gch.adapter.FormationAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.gch.network.RetrofitClient


class FormationFragment: Fragment() {

    private lateinit var binding: FragmentFormationBinding
    private lateinit var formationAdapter: FormationAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFormationBinding.inflate(layoutInflater)



        binding.rvFormation.adapter = FormationAdapter(getListNews(requireContext()))
        binding.rvFormation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialiser l'adaptateur avec une liste vide
        formationAdapter = FormationAdapter(mutableListOf()) // Utilisez mutableListOf() pour une liste mutable
        binding.rvFormation.adapter = formationAdapter
        binding.rvFormation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        Log.e("FRAGMENT Formation", formationAdapter.formationList.toString())

        // Utilisez Retrofit pour récupérer les données depuis MongoDB
        fetchDataFromApi()
    }

    public fun fetchDataFromApi() {
        val api = RetrofitClient.instance
        val call = api.getFormationsList()

        call.enqueue(object : Callback<List<Formation>> {
            override fun onResponse(call: Call<List<Formation>>, response: Response<List<Formation>>) {
                if (response.isSuccessful) {
                    val formationList: List<Formation>? = response.body()?.toMutableList()
                    Log.e("FRAGMENT Formation", response.body().toString())
                    // Mettez à jour votre RecyclerView avec les données de MongoDB
                    formationAdapter.updateList(formationList ?: emptyList())
                } else {
                    Log.d("aaaaaaa", (response ?:"Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Formation>>, t: Throwable) {
                // Gérer les erreurs de requête
                Log.e("onFailure", t.toString())
            }
        })
    }


    private fun getListNews(context: Context) : MutableList<Formation> {
        return mutableListOf(
            Formation(1, R.drawable.ic_news1, context.getString(R.string.news1) , context.getString(R.string.newsDesc1), context.getString(R.string.dificulty1 ),"","","" ,""),
            Formation(2, R.drawable.ic_news2, context.getString(R.string.news2) , context.getString(R.string.newsDesc2) , context.getString(R.string.dificulty1) , "","","" , ""),
            Formation(3, R.drawable.ic_news3, context.getString(R.string.news3) , context.getString(R.string.newsDesc3) , context.getString(R.string.dificulty1), "","","" , "")
        )
    }


}