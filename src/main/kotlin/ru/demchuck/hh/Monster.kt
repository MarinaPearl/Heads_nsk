package ru.demchuck.hh

import ru.demchuck.hh.abstraction.AbstractEntity
import ru.demchuck.hh.abstraction.Constants

class Monster(attack: Int, protection: Int, health: Int, damage: IntRange) :
    AbstractEntity(attack, protection, health, damage) {

    override fun checkDie() {
        if (health <= Constants.MIN_HEALTH) {
            logger.info { "the monster died" }
        }
    }
}