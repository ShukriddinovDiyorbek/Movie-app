package uz.pdp.movieapp.model

class Movie {
    var id: Int?  = null
    var title: String? = null
    var info: String? = null
    var date: String? = null

    constructor(title: String, info: String, date:String){
        this.title = title
        this.info = info
        this.date = date
    }

    constructor(id:Int,title: String, info: String, date:String){
        this.id = id
        this.title = title
        this.info = info
        this.date = date
    }
}