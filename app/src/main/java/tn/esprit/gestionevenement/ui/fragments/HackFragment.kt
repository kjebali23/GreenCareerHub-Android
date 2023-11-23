
package tn.esprit.gestionevenement.ui.fragments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.gestionevenement.AddHackActivity
import tn.esprit.gestionevenement.databinding.FragmentHackBinding
import tn.esprit.gestionevenement.network.RetrofitClient
import tn.esprit.gestionevenement.ui.detail.DetailActivityHack
import tn.esprit.gestionevenement.ui.Edit.EditHackActivity

import Hack
import HackAdapters
import OnHackClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HackFragment : AppCompatActivity(), OnHackClickListener {
    private lateinit var binding: FragmentHackBinding
    private lateinit var hackAdapter: HackAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hackAdapter = HackAdapters(emptyList(), this)
        binding.rvHack.adapter = hackAdapter
        binding.rvHack.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                hackAdapter.filter.filter(newText)
                return false
            }
        })

        fetchDataFromApi()

        binding.fabAddHackathon.setOnClickListener {
            val intent = Intent(this, AddHackActivity::class.java)
            startActivity(intent)
        }
    }

    public fun fetchDataFromApi() {
        val api = RetrofitClient.instancehack
        val call: Call<List<Hack>> = api.getHackList()

        call.enqueue(object : Callback<List<Hack>> {
            override fun onResponse(call: Call<List<Hack>>, response: Response<List<Hack>>) {
                if (response.isSuccessful) {
                    val hackList: List<Hack>? = response.body()?.toMutableList()
                    hackAdapter.updateList(hackList ?: emptyList())
                } else {
                    Log.d("aaaaaaa", (response ?: "Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Hack>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
    }

    override fun onHackClick(hack: Hack) {
        val intent = Intent(this, DetailActivityHack::class.java)
        intent.putExtra("HACK_KEY", hack)
        startActivity(intent)
    }

    override fun onDeleteClick(hack: Hack) {
        Log.d("onDeleteClick", "ID de la hackathon à supprimer : ${hack.id}")

        val api = RetrofitClient.instancehack
        val call: Call<Void> = api.deleteTodo(hack.id)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
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

    override fun onEditClick(hack: Hack) {
        val intent = Intent(this, EditHackActivity::class.java)
        intent.putExtra("hack_id", hack.id)
        startActivity(intent)
    }
}
