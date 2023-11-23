import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Conf (
    val id: String,
    val titre: String,
    val image: String,
    val description: String,
    val prix: Int,
    val tel: Int,
    val date: Date,
    val location: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        Date(parcel.readLong()),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(titre)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeInt(prix)
        parcel.writeInt(tel)
        parcel.writeLong(date.time)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Conf> {
        override fun createFromParcel(parcel: Parcel): Conf {
            return Conf(parcel)
        }

        override fun newArray(size: Int): Array<Conf?> {
            return arrayOfNulls(size)
        }
    }

    fun dateToString(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
}
