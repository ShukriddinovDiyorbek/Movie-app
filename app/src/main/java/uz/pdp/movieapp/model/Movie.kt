package uz.pdp.movieapp.model

class Movie {
    var id: Int?  = null
    var title: String? = null
    var authors: String? = null
    var comment: String? = null
    var date: String? = null

    constructor(title: String, authors: String,comment:String, date:String){
        this.title = title
        this.authors = authors
        this.comment = comment
        this.date = date
    }

    constructor(id:Int,title: String, authors: String,comment:String, date:String){
        this.id = id
        this.title = title
        this.authors = authors
        this.comment = comment
        this.date = date
    }
}