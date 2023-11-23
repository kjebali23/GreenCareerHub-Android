import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tn.esprit.gestionevenement.databinding.SingleHackBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

interface OnHackClickListener {
    fun onHackClick(hack: Hack)
    fun onDeleteClick(hack: Hack)
    fun onEditClick(hack: Hack)
}

class HackAdapters(
    private var hackListFull: List<Hack>,
    private val clickListener: OnHackClickListener
) : RecyclerView.Adapter<HackAdapters.OppsHolder>(), Filterable {

    private var hackList: List<Hack> = hackListFull

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OppsHolder {
        val binding = SingleHackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OppsHolder(binding)
    }

    override fun onBindViewHolder(holder: OppsHolder, position: Int) {
        holder.bind(hackList[position])
    }

    override fun getItemCount() = hackList.size

    fun updateList(newList: List<Hack>) {
        hackListFull = newList
        hackList = newList
        notifyDataSetChanged()
    }

    inner class OppsHolder(val binding: SingleHackBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onDeleteClick(hackList[position])
                }
            }

            binding.btnEdit.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onEditClick(hackList[position])
                }
            }

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onHackClick(hackList[position])
                }
            }
        }

        fun bind(hack: Hack) {
            with(binding) {
                title.text = hack.titrehack

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(hack.datehack)
                date.text = formattedDate

                Picasso.get().load(hack.imagehack).into(image)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Hack>()
                if (charSequence.isNullOrBlank()) {
                    filteredList.addAll(hackListFull)
                } else {
                    val filterPattern = charSequence.toString().toLowerCase(Locale.getDefault()).trim()
                    for (item in hackListFull) {
                        if (item.titrehack.toLowerCase(Locale.getDefault()).contains(filterPattern) ||
                            item.dateToString().toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                hackList = results?.values as List<Hack>
                notifyDataSetChanged()
            }
        }
    }
}
