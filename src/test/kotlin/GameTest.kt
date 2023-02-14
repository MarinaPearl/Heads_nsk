import org.junit.Assert.assertTrue
import org.junit.Test
import ru.demchuck.hh.Monster
import ru.demchuck.hh.Player
import ru.demchuck.hh.exception.ExcessNumberHealingException
import ru.demchuck.hh.exception.PlayingWithDeadEntityException

class GameTest {
    @Test
    fun `checking the attack on the monster`() {
        val player = Player(10, 10, 5, 1..6)
        val monster = Monster(5, 5, 3, 1..4)
        player.attackEnemy(monster)
        if (player.resultAttack) {
            assertTrue(monster.health < 3)
        } else {
            assertTrue(monster.health == 3)
        }
    }

    @Test
    fun `checking the attack on the player`() {
        val player = Player(10, 5, 10, 1..10)
        val monster = Monster(12, 5, 6, 1..6)
        monster.attackEnemy(player)
        if (monster.resultAttack) {
            assertTrue(player.health < 10)
        } else {
            assertTrue(player.health == 10)
        }
    }

    @Test
    fun `checking the player's healing`() {
        val player = Player(15, 10, 30, 1..9)
        val monster = Monster(17, 20, 25, 10..36)
        monster.attackEnemy(player)
        if (monster.resultAttack) {
            assertTrue(player.health < 30)
            val healthAfterAttack = player.health
            player.healYourself()
            assertTrue(player.health > healthAfterAttack)
        } else {
            assertTrue(player.health == 30)
        }
    }

    @Test(expected = ExcessNumberHealingException::class)
    fun `the player can heal himself up to 3 times`() {
        val player = Player(15, 10, 30, 1..9)
        val monster = Monster(17, 20, 25, 1..5)
        monster.attackEnemy(player)
        player.healYourself()
        monster.attackEnemy(player)
        player.healYourself()
        monster.attackEnemy(player)
        player.healYourself()
        monster.attackEnemy(player)
        player.healYourself()
    }

    @Test(expected = PlayingWithDeadEntityException::class)
    fun `it is impossible to attack a dead entity`() {
        val player = Player(17, 20, 12, 1..10)
        val monster = Monster(10, 15, 15, 5..15)
        while (player.health > 0) {
            monster.attackEnemy(player)
        }
        monster.attackEnemy(player)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun `checking the input data - attack`() {
        val player = Player(30, 20, 12, 1..10)
        val player2 = Player(-10, 20, 12, 1..10)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun `checking the input data - armor`() {
        val player = Player(10, 0, 12, 1..10)
        val player2 = Player(10, 40, 12, 1..10)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun `checking the input data - health`() {
        val player = Player(10, 0, -12, 1..10)
    }


}