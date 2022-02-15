package uz.pdp.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.pdp.movieapp.R
import uz.pdp.movieapp.model.Movie

class CustomAdapter(var items: ArrayList<Movie>) : RecyclerView.Adapter<CustomAdapter.VH>()  {


    inner class VH(view: View): RecyclerView.ViewHolder(view){
        val tv_movie:TextView
        val tv_author:TextView
        val tv_date:TextView

        init {
            tv_author = view.findViewById(R.id.tv_authors)
            tv_movie = view.findViewById(R.id.tv_movie)
            tv_date = view.findViewById(R.id.tv_date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.tv_movie.setText(item.title.toString())
        holder.tv_author.setText(item.info.toString())
        holder.tv_date.setText(item.date.toString())
    }

    override fun getItemCount(): Int {
        return items.size
    }
}