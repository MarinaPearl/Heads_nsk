package ru.demchuck.hh

import ru.demchuck.hh.abstraction.Constants

fun main(args: Array<String>) {
    try {
        val player = Player(10, 15, 10, 1..6)
        val monster = Monster(10, 12, 13, 2..7)
        player.attackEnemy(monster)
        monster.attackEnemy(player)
        player.attackEnemy(monster)
        monster.attackEnemy(player)
        player.healYourself()
        while (monster.health > Constants.MIN_HEALTH) {
            player.attackEnemy(monster)
        }
    }catch (exception : Exception) {
        exception.printStackTrace()
    }
}