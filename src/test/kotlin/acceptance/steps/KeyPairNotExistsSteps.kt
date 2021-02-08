package acceptance.steps

import domain.RSAStoreState
import io.cucumber.java8.En
import kotlinx.coroutines.runBlocking
import acceptance.repo.InMemoryKeyPairNotExistsLocalDataStore
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEmpty

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class KeyPairNotExistsSteps(localstore: InMemoryKeyPairNotExistsLocalDataStore) : En {

    init {
        Given("It's the first launch of the app") {

        }
        And("I check if  the mobile privateKey is empty") {
            runBlocking {
                localstore.getPrivateKeyAsString().shouldBeEmpty()
            }
        }
        And("I also check if the mobile publicKey is empty") {
            runBlocking {
                localstore.getPublicKeyAsString().shouldBeEmpty()
            }
        }
        When("I generate the mobile Keypair") {

        }
        Then("I store the mobile privatekey as string in local storage") {
            runBlocking {
                localstore.savePrivateKeyAsString("").`should be instance of`(RSAStoreState.Success::class)
            }
        }
        Then("I also store the mobile publicKey as string in local storage") {
            runBlocking {
                localstore.savePublicKeyAsString("").`should be instance of`(RSAStoreState.Success::class)
            }
        }
    }
}