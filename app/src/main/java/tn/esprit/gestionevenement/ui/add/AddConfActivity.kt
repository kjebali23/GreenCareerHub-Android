// AddConfActivity.kt
package tn.esprit.gestionevenement

import Conf
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.gestionevenement.network.RetrofitClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.gestionevenement.databinding.AddConferenceBinding

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddConfActivity : AppCompatActivity() {
    private lateinit var binding: AddConferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddConferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ADDButton.setOnClickListener {
            // Appel de la fonction pour ajouter la conférence
            addConference()
        }
    }

    private fun addConference() {
        // Récupération des valeurs depuis les champs de l'interface utilisateur
        val title = binding.titleCONFADD1.text.toString()
        val tel = binding.nameCONFADD.text.toString()
        val description = binding.descriptionCONFADD.text.toString()
        val location = binding.locCONFADD.text.toString()
        val date = binding.dateCONF.text.toString()
        val prix = binding.prixCONF.text.toString()
        val image = binding.imageCONF.text.toString()

        // Validation des champs (tu peux ajouter une logique de validation supplémentaire ici)

        // Création d'un objet Conf avec les valeurs
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateObject: Date = try {
            dateFormat.parse(date) ?: Date()
        } catch (e: Exception) {
            Date()
        }

        val newConf = Conf(
            id = "0",  // L'id peut être n'importe quoi, car il sera généralement généré par la base de données
            titre = title,
            tel = tel.toIntOrNull() ?: 0,  // Conversion en entier, assure-toi que le champ est un nombre
            description = description,
            location = location,
            date = dateObject,  // Utilisation de l'objet Date converti
            prix = prix.toIntOrNull() ?: 0,  // Conversion en entier, assure-toi que le champ est un nombre
            image = image
        )

        // Envoi de la conférence à l'API pour l'ajout
        val api = RetrofitClient.instance
        val call: Call<Conf> = api.addConference(newConf)

        call.enqueue(object : Callback<Conf> {
            override fun onResponse(call: Call<Conf>, response: Response<Conf>) {
                Log.d("AddConfActivity", "onResponse: ${response.body()}")

                if (response.isSuccessful) {
                    // La conférence a été ajoutée avec succès
                    // Ajoute ici la logique que tu veux exécuter après l'ajout
                    Log.d("AddConfActivity", "Conference added successfully")
                } else {
                    // Gestion des erreurs en cas d'échec de l'ajout
                    if (response.errorBody() != null) {
                        // Afficher le message d'erreur du serveur
                        Log.e("AddConfActivity", "Error: ${response.errorBody()?.string()}")
                    } else {
                        // Afficher un message d'erreur générique
                        Log.e("AddConfActivity", "Error: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<Conf>, t: Throwable) {
                // Gestion des erreurs en cas d'échec de la requête
                Log.e("AddConfActivity", "Failure: ${t.message}")
            }
        })


    }
}
