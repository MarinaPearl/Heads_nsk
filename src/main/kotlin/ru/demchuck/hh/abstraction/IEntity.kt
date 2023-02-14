package ru.demchuck.hh.abstraction

interface  IEntity {

    var attack: Int
    var armor: Int
    var health: Int
    var damage: IntRange

    fun attackEnemy(defending: IEntity)
    fun checkDie()
}