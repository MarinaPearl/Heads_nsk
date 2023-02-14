package ru.demchuck.hh.abstraction

import mu.KotlinLogging
import ru.demchuck.hh.exception.PlayingWithDeadEntityException


abstract class AbstractEntity(
    override var attack: Int, override var armor: Int,
    override var health: Int, override var damage: IntRange
) : IEntity {

    protected val logger = KotlinLogging.logger {}
    var resultAttack = false
        private set


    init {
        checkInputData()
    }

    private fun checkInputData() {
        require(
            attack in Constants.MIN_ATTACK..Constants.MAX_ATTACK &&
                    armor in Constants.MIN_ARMOR..Constants.MAX_ARMOR
        ) {
            "attack and armor are integers from 1 to 20"
        }
        require(health >= Constants.MIN_HEALTH) {
            "health is an integer greater than 0"
        }
    }

    override fun attackEnemy(defending: IEntity) {
        logger.info { "start attack" }
        resultAttack = false
        checkLives(defending)
        var modifierAttack = attack - defending.armor + 1
        if (modifierAttack < Constants.MIN_NUMBER_THROWS) {
            modifierAttack = Constants.MIN_NUMBER_THROWS
        }
        rollDice(modifierAttack)
        if (resultAttack) {
            logger.info { "successful attack" }
            defending.health -= damage.random()
        } else {
            logger.info { "unsuccessful attack" }
        }
        defending.checkDie()
    }

    private fun checkLives(defending: IEntity) {
        if (health <= Constants.MIN_HEALTH || defending.health <= Constants.MIN_HEALTH) {
            throw PlayingWithDeadEntityException()
        }
    }

    private fun rollDice(countDice : Int) {
        for (it in 1..countDice) {
            val diceRoll = (1..6).random()
            if (diceRoll >= Constants.MIN_SUCCESSFUL_THROW) {
                resultAttack = true
                break
            }
        }
    }
}