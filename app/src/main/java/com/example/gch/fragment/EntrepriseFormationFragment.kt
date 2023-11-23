package com.example.gch.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.Models.Formation
import com.example.gch.R
import com.example.gch.adapter.EntrepFormationAdapter
import com.example.gch.adapter.FormationAdapter
import com.example.gch.databinding.FragmentFormationBinding
import com.example.gch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntrepriseFormationFragment : Fragment() {
    private lateinit var binding: FragmentFormationBinding
    private lateinit var formationAdapter: EntrepFormationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFormationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter without setting it to the RecyclerView
        formationAdapter = EntrepFormationAdapter(mutableListOf())
        binding.rvFormation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFormation.adapter = formationAdapter

        // Add a TextView to show a message when data is empty
        binding.emptyTextView.visibility = View.GONE

        Log.e("FRAGMENT Formation", formationAdapter.formationList.toString())

        // Use Retrofit to retrieve data from MongoDB
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val api = RetrofitClient.instance
        val call: Call<List<Formation>> = api.getEntrepFormations(addedBy = "khalil")

        call.enqueue(object : Callback<List<Formation>> {
            override fun onResponse(call: Call<List<Formation>>, response: Response<List<Formation>>) {
                if (response.isSuccessful) {
                    val formationList: List<Formation>? = response.body()

                    // Check if the response is not null and not empty before updating RecyclerView
                    if (formationList != null && formationList.isNotEmpty()) {
                        Log.e("FRAGMENT Formation", response.body().toString())

                        // Update the RecyclerView with the data from MongoDB
                        formationAdapter.updateList(formationList)
                        binding.emptyTextView.visibility = View.GONE
                    } else {
                        Log.d("aaaaaaa", "Empty response or null")

                        // Show a message indicating that there is nothing to show
                        binding.emptyTextView.visibility = View.VISIBLE
                    }
                } else {
                    Log.d("aaaaaaa", (response ?: "Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Formation>>, t: Throwable) {
                // Handle request failures
                Log.e("onFailure", t.toString())
            }
        })
    }
}




//class EntrepriseFormationFragment: Fragment() {
//    private lateinit var binding: FragmentFormationBinding
//    private lateinit var formationAdapter: EntrepFormationAdapter
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        binding = FragmentFormationBinding.inflate(layoutInflater)
//
//
//
//        binding.rvFormation.adapter = FormationAdapter(getListNews(requireContext()))
//        binding.rvFormation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//
//        return binding.root
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Initialiser l'adaptateur avec une liste vide
//        formationAdapter = EntrepFormationAdapter(mutableListOf()) // Utilisez mutableListOf() pour une liste mutable
//        binding.rvFormation.adapter = formationAdapter
//        binding.rvFormation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//
//        Log.e("FRAGMENT Formation", formationAdapter.formationList.toString())
//
//        // Utilisez Retrofit pour récupérer les données depuis MongoDB
//        fetchDataFromApi()
//    }
//
//    public fun fetchDataFromApi() {
//        val api = RetrofitClient.instance
//        val call: Call<List<Formation>> = api.getEntrepFormations(addedBy = "khalil")
//
//        call.enqueue(object : Callback<List<Formation>> {
//            override fun onResponse(call: Call<List<Formation>>, response: Response<List<Formation>>) {
//                if (response.isSuccessful) {
//                    val formationList: List<Formation>? = response.body()?.toMutableList()
//                    Log.e("FRAGMENT Formation", response.body().toString())
//                    // Mettez à jour votre RecyclerView avec les données de MongoDB
//                    formationAdapter.updateList(formationList ?: emptyList())
//                } else {
//                    Log.d("aaaaaaa", (response ?:"Empty response").toString())
//                }
//            }
//
//            override fun onFailure(call: Call<List<Formation>>, t: Throwable) {
//                // Gérer les erreurs de requête
//                Log.e("onFailure", t.toString())
//            }
//        })
//    }


    private fun getListNews(context: Context) : MutableList<Formation> {
        return mutableListOf(
            Formation(1, R.drawable.ic_news1, context.getString(R.string.news1) , context.getString(
                R.string.newsDesc1), context.getString(R.string.dificulty1 ),"","","" ,""),
            Formation(2, R.drawable.ic_news2, context.getString(R.string.news2) , context.getString(
                R.string.newsDesc2) , context.getString(R.string.dificulty1) , "","","" , ""),
            Formation(3, R.drawable.ic_news3, context.getString(R.string.news3) , context.getString(
                R.string.newsDesc3) , context.getString(R.string.dificulty1), "","","" , "")
        )
    }



