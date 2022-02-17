package uz.pdp.movieapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.pdp.movieapp.db.MyFatabaseService
import uz.pdp.movieapp.model.Movie

class EditFragment : Fragment() {
    lateinit var root: View
    lateinit var myFatabaseService: MyFatabaseService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_edit, container, false)
        val et_title = root.findViewById<EditText>(R.id.et_title)
        val et_authors = root.findViewById<EditText>(R.id.et_authors)
        val et_about = root.findViewById<EditText>(R.id.et_about)
        val et_date = root.findViewById<EditText>(R.id.et_date)
        val change_btn = root.findViewById<Button>(R.id.change_btn)

        if (arguments != null){
            getActivity()?.setTitle("Edit movie");
            val bundle: Bundle = this.requireArguments()
            change_btn.setText("Edit")
            val idbundle = bundle.getInt("id")
            val title = bundle.getString("title")
            val authors = bundle.getString("authors")
            val about = bundle.getString("about")
            val date = bundle.getString("date")
            et_title.setText(title)
            et_authors.setText(authors)
            et_about.setText(about)
            et_date.setText(date)

            change_btn.setOnClickListener {

                val s5 = et_title.text.trim(' ').isNotEmpty()
                val s6 = et_authors.text.trim(' ').isNotEmpty()
                val s7 = et_about.text.trim(' ').isNotEmpty()
                val s8 = et_date.text.trim(' ').isNotEmpty()
                if ( s5 && s6 && s7 && s8 &&
                    et_title.text.isNotEmpty() && et_authors.text.isNotEmpty() &&
                    et_about.text.isNotEmpty() && et_date.text.isNotEmpty()) {
                    val title1 = et_title.text.trim().toString()
                    val authors1 = et_authors.text.trim().toString()
                    val about1 = et_about.text.trim().toString()
                    val date1 = et_date.text.trim().toString()

                    val movie = Movie(idbundle,title1, authors1, about1, date1)
                    if(myFatabaseService.updateContact(movie) != -1) {
                        Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "To'liq to'diring", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            getActivity()?.setTitle("Add movie");
            change_btn.setText("Save")
            change_btn.setOnClickListener {
                val s5 = et_title.text.trim(' ').isNotEmpty()
                val s6 = et_authors.text.trim(' ').isNotEmpty()
                val s7 = et_about.text.trim(' ').isNotEmpty()
                val s8 = et_date.text.trim(' ').isNotEmpty()
                if ( s5 && s6 && s7 && s8 &&
                    et_title.text.isNotEmpty() && et_authors.text.isNotEmpty() &&
                    et_about.text.isNotEmpty() && et_date.text.isNotEmpty()) {
                    val title = et_title.text.trim().toString()
                    val authors = et_authors.text.trim().toString()
                    val about = et_about.text.trim().toString()
                    val date = et_date.text.trim().toString()
                    val movie = Movie(title, authors, about, date)
                    myFatabaseService.addContact(movie)
                    Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "To'liq to'diring", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myFatabaseService = MyFatabaseService(context)
    }
}