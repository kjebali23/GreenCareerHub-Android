
package tn.esprit.gestionevenement

import Hack
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.gestionevenement.network.RetrofitClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.gestionevenement.databinding.AddHackathonBinding

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddHackActivity : AppCompatActivity() {
    private lateinit var binding: AddHackathonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddHackathonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ADDButton.setOnClickListener {
            // Appel de la fonction pour ajouter la hackathon
            addHackerence()
        }
    }

    private fun addHackerence() {
        // Récupération des valeurs depuis les champs de l'interface utilisateur
        val title = binding.titleCONFADD1.text.toString()
        val tel = binding.nameCONFADD.text.toString()
        val description = binding.descriptionCONFADD.text.toString()
        val location = binding.locCONFADD.text.toString()
        val date = binding.dateCONF.text.toString()

        val image = binding.imageCONF.text.toString()

        // Validation des champs (tu peux ajouter une logique de validation supplémentaire ici)

        // Création d'un objet Hack avec les valeurs
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateObject: Date = try {
            dateFormat.parse(date) ?: Date()
        } catch (e: Exception) {
            Date()
        }

        val newHack = Hack(
            id = "0",  // L'id peut être n'importe quoi, car il sera généralement généré par la base de données
            titrehack = title,
            telhack = tel.toIntOrNull() ?: 0,  // Conversion en entier, assure-toi que le champ est un nombre
            descriptionhack = description,
            locationhack = location,
            datehack = dateObject,  // Utilisation de l'objet Date converti

            imagehack = image
        )

        // Envoi de la hackathon à l'API pour l'ajout
        val api = RetrofitClient.instancehack
        val call: Call<Hack> = api.addHackathon(newHack)

        call.enqueue(object : Callback<Hack> {
            override fun onResponse(call: Call<Hack>, response: Response<Hack>) {
                Log.d("AddHackActivity", "onResponse: ${response.body()}")

                if (response.isSuccessful) {
                    // La hackathon a été ajoutée avec succès
                    // Ajoute ici la logique que tu veux exécuter après l'ajout
                    Log.d("AddHackActivity", "Hackathon added successfully")
                } else {
                    // Gestion des erreurs en cas d'échec de l'ajout
                    if (response.errorBody() != null) {
                        // Afficher le message d'erreur du serveur
                        Log.e("AddHackActivity", "Error: ${response.errorBody()?.string()}")
                    } else {
                        // Afficher un message d'erreur générique
                        Log.e("AddHackActivity", "Error: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<Hack>, t: Throwable) {
                // Gestion des erreurs en cas d'échec de la requête
                Log.e("AddHackActivity", "Failure: ${t.message}")
            }
        })


    }
}
