package uz.pdp.movieapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import uz.pdp.movieapp.adapter.CustomAdapter
import uz.pdp.movieapp.db.MyFatabaseService
import uz.pdp.movieapp.model.Movie

class DeleteFragment : Fragment() {
    lateinit var root: View
    lateinit var customAdapter: CustomAdapter
    lateinit var myFatabaseService: MyFatabaseService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()?.setTitle("Delete movie")
        root =  inflater.inflate(R.layout.fragment_delete, container, false)
        val title1 = root.findViewById<TextView>(R.id.title_tv)
        val authors1 = root.findViewById<TextView>(R.id.authors_tv)
        val about1 = root.findViewById<TextView>(R.id.about_tv)
        val date1 = root.findViewById<TextView>(R.id.date_tv)
        val close_btn1 = root.findViewById<Button>(R.id.close_btn)

        if (arguments != null){
            val bundle = requireArguments()
            val idBoundle = bundle.getInt("id")
            val title = bundle.getString("title")
            val authors = bundle.getString("authors")
            val about = bundle.getString("about")
            val date = bundle.getString("date")
            title1.setText("Movie name: $title")
            authors1.setText("Authors: " + authors)
            about1.setText("About movie: " + about)
            date1.setText("Date: " + date)
            val movie = Movie(idBoundle,title.toString(),authors.toString(),about.toString(),date.toString())
            close_btn1.setOnClickListener {
                myFatabaseService.deleteContact(movie)

                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show()
                getActivity()?.onBackPressed();
            }
        }
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myFatabaseService = MyFatabaseService(context)
    }
}