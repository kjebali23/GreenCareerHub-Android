import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tn.esprit.gestionevenement.databinding.SingleConfBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

interface OnConfClickListener {
    fun onConfClick(conf: Conf)
    fun onDeleteClick(conf: Conf)
    fun onEditClick(conf: Conf)
}

class ConfAdapters(
    private var confListFull: List<Conf>,
    private val clickListener: OnConfClickListener
) : RecyclerView.Adapter<ConfAdapters.OppsHolder>(), Filterable {

    private var confList: List<Conf> = confListFull

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OppsHolder {
        val binding = SingleConfBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OppsHolder(binding)
    }

    override fun onBindViewHolder(holder: OppsHolder, position: Int) {
        holder.bind(confList[position])
    }

    override fun getItemCount() = confList.size

    fun updateList(newList: List<Conf>) {
        confListFull = newList
        confList = newList
        notifyDataSetChanged()
    }

    inner class OppsHolder(val binding: SingleConfBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onDeleteClick(confList[position])
                }
            }

            binding.btnEdit.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onEditClick(confList[position])
                }
            }

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onConfClick(confList[position])
                }
            }
        }

        fun bind(conf: Conf) {
            with(binding) {
                title.text = conf.titre

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(conf.date)
                date.text = formattedDate

                Picasso.get().load(conf.image).into(image)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Conf>()
                if (charSequence.isNullOrBlank()) {
                    filteredList.addAll(confListFull)
                } else {
                    val filterPattern = charSequence.toString().toLowerCase(Locale.getDefault()).trim()
                    for (item in confListFull) {
                        if (item.titre.toLowerCase(Locale.getDefault()).contains(filterPattern) ||
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
                confList = results?.values as List<Conf>
                notifyDataSetChanged()
            }
        }
    }
}
