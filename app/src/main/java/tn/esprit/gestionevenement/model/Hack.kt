import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Hack (
    val id: String,
    val titrehack: String,
    val imagehack: String,
    val descriptionhack: String,

    val telhack: Int,
    val datehack: Date,
    val locationhack: String






) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,

        parcel.readInt(),
        Date(parcel.readLong()),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(titrehack)
        parcel.writeString(imagehack)
        parcel.writeString(descriptionhack)

        parcel.writeInt(telhack)
        parcel.writeLong(datehack.time)
        parcel.writeString(locationhack)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hack> {
        override fun createFromParcel(parcel: Parcel): Hack {
            return Hack(parcel)
        }

        override fun newArray(size: Int): Array<Hack?> {
            return arrayOfNulls(size)
        }
    }

    fun dateToString(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(datehack)
    }
}
