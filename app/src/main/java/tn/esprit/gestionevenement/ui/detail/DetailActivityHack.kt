package tn.esprit.gestionevenement.ui.detail

import Hack
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import tn.esprit.gestionevenement.databinding.DetailHackBinding

import java.text.SimpleDateFormat
import java.util.*

class DetailActivityHack : AppCompatActivity() {
    private lateinit var binding: DetailHackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailHackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Récupérer l'objet Hack de l'intent
        val hack = intent.getParcelableExtra<Hack>("HACK_KEY")

        // Mettre à jour l'interface avec les détails de la hackathon
        hack?.let {
            binding.detailTitle.text = it.titrehack
            binding.detailDesc.text = it.descriptionhack

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(it.datehack)
            binding.detailDate.text = formattedDate

            binding.detailLocation.text = it.locationhack

            binding.detailPhone.text = it.telhack.toString()

            // Chargez l'image avec Picasso (ou toute autre bibliothèque de votre choix)
            // Exemple avec Picasso :
             Picasso.get().load(it.imagehack).into(binding.detailImage)
        }
    }
}
