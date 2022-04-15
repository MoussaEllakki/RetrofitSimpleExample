import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.Character
import com.example.retrofitproject.R


class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    var data: List<Character>? = null


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view =  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_for_rv, parent, false))
            return view
        }




        override fun getItemCount(): Int {
            return data?.size ?: 0
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            holder.name.text =  data!![position].name
            holder.id_txt.text = data!![position]._id.toString()



        }



        class  MyViewHolder (view: android.view.View) : RecyclerView.ViewHolder(view) {


            val name = view.findViewById<TextView>(R.id.name_txt)
            val id_txt = view.findViewById<TextView>(R.id.id_txt)

        }

    }

