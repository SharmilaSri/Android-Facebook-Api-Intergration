import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sharmila.android.kotlin.sampleappkotlin.R
import com.sharmila.android.kotlin.sampleappkotlin.adapaters.HotelListAdapterCallback
import com.sharmila.android.kotlin.sampleappkotlin.model.Hotel
import com.sharmila.android.kotlin.sampleappkotlin.model.Hotel.Companion.instance
import com.sharmila.android.kotlin.sampleappkotlin.model.Image

class HotelListAdapter(private val context: Context, private val dataSet: List<Hotel>) :
        RecyclerView.Adapter<HotelListAdapter.ViewHolder>() {

    private var hotelSelectedCallBack: HotelListAdapterCallback? = null

    public fun setHotelSelectedCallBack(hotelSelectedCallBack: HotelListAdapterCallback?) {
        this.hotelSelectedCallBack = hotelSelectedCallBack
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtViewHotelName: TextView
        val txtViewAddressLineOne: TextView
        val txtViewAddressLineTwo: TextView
        val imageViewHotel: ImageView
        val parentLayout: ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View.
            txtViewHotelName = view.findViewById(R.id.txt_view_hotel_name)
            txtViewAddressLineOne = view.findViewById(R.id.txt_view_address_line_1)
            txtViewAddressLineTwo = view.findViewById(R.id.txt_view_address_line_2)
            imageViewHotel = view.findViewById(R.id.image_view_hotel)
            parentLayout = view.findViewById(R.id.layout_parent)
        }
    }

    private fun moveToHotelDetailDisplayActivity(position: Int) {
        //Create a singleton hotel and set required values
        val selectedHotel = instance
        selectedHotel!!.setTitle(dataSet[position].title).setDescription(dataSet[position].description)
                .setLongtitude(dataSet[position].longtitude).setLatitute(dataSet[position].latitute)
        val image = Image()
        image.small = dataSet[position].images!!.small
        selectedHotel.images = image

        hotelSelectedCallBack!!.callback()

    }
    // Create new views
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.hotel_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // contents of the view with that element
        viewHolder.txtViewHotelName.text = dataSet[position].title
        viewHolder.txtViewAddressLineOne.text = dataSet[position].address.split("\n")[0]
        viewHolder.txtViewAddressLineTwo.text = dataSet[position].address.split("\n")[1]
        Glide.with(context)
                .load(dataSet[position].images!!.small)
                .into(viewHolder.imageViewHotel)
        viewHolder.parentLayout.setOnClickListener{moveToHotelDetailDisplayActivity(position)}

    }

    // Return the size of  dataset
    override fun getItemCount() = dataSet.size

}
