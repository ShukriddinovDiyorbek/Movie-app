package uz.pdp.movieapp.db

import uz.pdp.movieapp.model.Movie

interface DBService {
    fun addContact(movie: Movie)

    fun getAllContacts():ArrayList<Movie>
}