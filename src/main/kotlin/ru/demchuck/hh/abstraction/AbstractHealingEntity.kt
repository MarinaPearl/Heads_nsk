package ru.demchuck.hh.abstraction

import ru.demchuck.hh.exception.ExcessNumberHealingException
import ru.demchuck.hh.exception.PlayingWithDeadEntityException

abstract class AbstractHealingEntity(attack: Int, protection: Int, health: Int, damage: IntRange) :
    AbstractEntity(attack, protection, health, damage) {

    private val maxHealth = health
    private var numberHealing = 3

    fun healYourself() {
        checkNumberHealing()
        --numberHealing
        health += maxHealth / 2
        if (health > maxHealth) {
            health = maxHealth
        }
        logger.info { "the player is healing! Now his health = $health" }
    }

    private fun checkNumberHealing() {
        if (numberHealing <= 0) {
            throw ExcessNumberHealingException()
        }
    }
}