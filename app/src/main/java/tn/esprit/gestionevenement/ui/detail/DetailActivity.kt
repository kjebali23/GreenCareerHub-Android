package tn.esprit.gestionevenement.ui.detail
import Conf
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import com.squareup.picasso.Picasso
import tn.esprit.gestionevenement.databinding.DetailConfBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: DetailConfBinding
    private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailConfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Récupérer l'objet Conf de l'intent
        val conf = intent.getParcelableExtra<Conf>("CONF_KEY")

        // Mettre à jour l'interface avec les détails de la conférence
        conf?.let {
            binding.detailTitle.text = it.titre
            binding.detailDesc.text = it.description

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(it.date)
            binding.detailDate.text = formattedDate

            binding.detailLocation.text = it.location
            binding.detailPrice.text = "Prix : ${it.prix} DT"
            binding.detailPhone.text = it.tel.toString()

            // Chargez l'image avec Picasso (ou toute autre bibliothèque de votre choix)
            // Exemple avec Picasso :
             Picasso.get().load(it.image).into(binding.detailImage)
        }

        // Ajouter un écouteur d'événements au bouton
        binding.btnEdit.setOnClickListener {
            // Vérifier la permission d'écriture externe avant de générer le PDF
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Demander la permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                )
            } else {
                // La permission est déjà accordée, générer le PDF
                generatePDF()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Vérifier si la permission d'écriture externe a été accordée
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // La permission a été accordée, générer le PDF
            generatePDF()
        } else {
            // La permission n'a pas été accordée, vous pouvez afficher un message d'information à l'utilisateur si nécessaire
            Toast.makeText(
                this,
                "La permission d'écriture externe est nécessaire pour générer le PDF",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun generatePDF() {
        try {
            // Créer un objet Document
            val document = Document()

            // Définir le chemin du fichier PDF
            // Remplacez le chemin du fichier par un répertoire approprié dans le stockage externe

            val filePath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "DetailPDF.pdf"
            )

            // Créer un objet PdfWriter
            val pdfWriter = PdfWriter.getInstance(document, FileOutputStream(filePath))

            // Ouvrir le document pour écrire
            document.open()

            // Ajouter du contenu au document
            document.add(Paragraph("Détails de la Conférence"))
            document.add(Paragraph("Titre: ${binding.detailTitle.text}"))
            document.add(Paragraph("Description: ${binding.detailDesc.text}"))
            document.add(Paragraph("Date: ${binding.detailDate.text}"))
            document.add(Paragraph("Location: ${binding.detailLocation.text}"))
            document.add(Paragraph("Prix: ${binding.detailPrice.text}"))
            document.add(Paragraph("Téléphone: ${binding.detailPhone.text}"))

            // Ajouter l'image au document (à remplacer avec votre propre logique de gestion d'image)
             val stream = ByteArrayOutputStream()
             val bitmap = Bitmap.createBitmap(binding.detailImage.width, binding.detailImage.height, Bitmap.Config.ARGB_8888)
             val canvas = Canvas(bitmap)
            binding.detailImage.draw(canvas)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
             val image = Image.getInstance(stream.toByteArray())
            document.add(image)

            // Fermer le document
            document.close()

            // Afficher un message de succès
            Toast.makeText(this, "PDF généré avec succès", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

