package uz.pdp.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import uz.pdp.movieapp.EditFragment
import uz.pdp.movieapp.R
import uz.pdp.movieapp.model.Movie

class CustomAdapter(var items: ArrayList<Movie>, val myListener: MyListener) :
    RecyclerView.Adapter<CustomAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.tv_movie.setText(item.title.toString())
        holder.tv_author.setText(item.authors.toString())
        holder.tv_date.setText(item.date.toString())

        holder.edit_btn.setOnClickListener {
            myListener.edit(position, item)
        }
        holder.delete_btn.setOnClickListener {
            myListener.delete(position, item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tv_movie: TextView
        val tv_author: TextView
        val tv_date: TextView
        val edit_btn: Button
        val delete_btn: Button

        init {
            tv_author = view.findViewById(R.id.tv_authors)
            tv_movie = view.findViewById(R.id.tv_movie)
            tv_date = view.findViewById(R.id.tv_date)
            edit_btn = view.findViewById(R.id.edit_btn)
            delete_btn = view.findViewById(R.id.delete_btn)
        }
    }

    interface MyListener {
        fun edit(position: Int, item: Movie)
        fun delete(position: Int, item: Movie)
    }
}