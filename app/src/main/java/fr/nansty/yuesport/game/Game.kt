package fr.nansty.yuesport.game

data class Game (var id: Long, var name: String){

    constructor(name: String) : this(-1, name)
}