package uz.pdp.movieapp

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.pdp.movieapp.adapter.CustomAdapter
import uz.pdp.movieapp.db.MyFatabaseService
import uz.pdp.movieapp.model.Movie

class HomeFragment : Fragment() {
    lateinit var root: View
    lateinit var myFatabaseService: MyFatabaseService
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()?.setTitle("Movies");
        root =  inflater.inflate(R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context,1)
          val adapter = CustomAdapter(myFatabaseService.getAllContacts(), object :CustomAdapter.MyListener{
              override fun delete(position: Int, item: Movie) {
                  val id = item.id
                  val title = item.title
                  val authors = item.authors
                  val about = item.comment
                  val date = item.date
                  val bundle = Bundle()
                  bundle.putInt("id",id!!.toInt())
                  bundle.putString("title",title)
                  bundle.putString("authors",authors)
                  bundle.putString("about",about)
                  bundle.putString("date",date)
                  findNavController().navigate(R.id.deleteFragment,bundle)
              }

              override fun edit(position: Int, item: Movie) {
                  val id = item.id
                  val title = item.title
                  val authors = item.authors
                  val about = item.comment
                  val date = item.date
                  val bundle = Bundle()
                  bundle.putInt("id",id!!.toInt())
                  bundle.putString("title",title)
                  bundle.putString("authors",authors)
                  bundle.putString("about",about)
                  bundle.putString("date",date)
                  findNavController().navigate(R.id.editFragment, bundle)
              }
          })
        recyclerView.adapter = adapter

        return root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        myFatabaseService = MyFatabaseService(context)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.editFragment)
        return super.onOptionsItemSelected(item)
    }
}