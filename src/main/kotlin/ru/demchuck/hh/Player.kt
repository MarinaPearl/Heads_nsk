package ru.demchuck.hh

import ru.demchuck.hh.abstraction.AbstractHealingEntity
import ru.demchuck.hh.abstraction.Constants

class Player(attack: Int, protection: Int, health: Int, damage: IntRange) :
    AbstractHealingEntity(attack, protection, health, damage) {

    override fun checkDie() {
        if (health <= Constants.MIN_HEALTH) {
            logger.info { "the player died" }
        }
    }
}