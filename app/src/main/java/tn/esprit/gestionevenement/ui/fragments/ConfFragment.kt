package tn.esprit.gestionevenement.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.gestionevenement.AddConfActivity
import tn.esprit.gestionevenement.databinding.FragmentConfBinding
import tn.esprit.gestionevenement.network.RetrofitClient

import tn.esprit.gestionevenement.ui.Edit.EditConfActivity

import Conf
import ConfAdapters

import OnConfClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.gestionevenement.ui.detail.DetailActivity


class ConfFragment : Fragment(), OnConfClickListener {
    private lateinit var binding: FragmentConfBinding
    private lateinit var confAdapter: ConfAdapters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confAdapter = ConfAdapters(emptyList(), this)
        binding.rvConf.adapter = confAdapter
        binding.rvConf.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                confAdapter.filter.filter(newText)
                return false
            }
        })

        fetchDataFromApi()

        // Set click listener for FloatingActionButton
        binding.fabAddConference.setOnClickListener {
            // Start AddOpp activity without passing any data
            val intent = Intent(requireContext(), AddConfActivity::class.java)
            startActivity(intent)
        }

        binding.btnVersHack.setOnClickListener {
            // Démarrer l'activité pour l'interface fragment_hack
            val intent = Intent(requireContext(), HackFragment::class.java)
            startActivity(intent)
        }
    }

    public fun fetchDataFromApi() {
        val api = RetrofitClient.instance
        val call: Call<List<Conf>> = api.getConfList()

        call.enqueue(object : Callback<List<Conf>> {
            override fun onResponse(call: Call<List<Conf>>, response: Response<List<Conf>>) {
                if (response.isSuccessful) {
                    val confList: List<Conf>? = response.body()?.toMutableList()
                    confAdapter.updateList(confList ?: emptyList())
                } else {
                    Log.d("aaaaaaa", (response ?: "Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Conf>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
    }

    override fun onConfClick(conf: Conf) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("CONF_KEY", conf)
        startActivity(intent)
    }

    override fun onDeleteClick(conf: Conf) {
        Log.d("onDeleteClick", "ID de la conférence à supprimer : ${conf.id}")

        val api = RetrofitClient.instance
        val call: Call<Void> = api.deleteTodo(conf.id)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // La suppression a réussi, mettez à jour la liste
                    fetchDataFromApi()
                } else {
                    Log.d("onDeleteClick", "Erreur lors de la suppression: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("onDeleteClick", "Erreur lors de la suppression", t)
            }
        })
    }

    override fun onEditClick(conf: Conf) {
        // Réagir au clic sur le bouton "Edit"
        // Vous pouvez démarrer une nouvelle activité pour l'édition ici
        val intent = Intent(requireContext(), EditConfActivity::class.java)
        intent.putExtra("conf_id", conf.id) // Envoyer l'ID ou d'autres données nécessaires à l'édition
        startActivity(intent)
    }
}
