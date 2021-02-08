package acceptance.steps

import io.cucumber.java8.En
import kotlinx.coroutines.runBlocking
import acceptance.repo.InMemoryKeyPairExistsLocalDataStore
import org.amshove.kluent.shouldNotBeNullOrEmpty

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class KeyPairExistsSteps(localstore: InMemoryKeyPairExistsLocalDataStore) : En {

    init {
        Given("It's not  the first launch of the app") {

        }
        And("I check if  the mobile privateKey  is not empty") {
            runBlocking {
                localstore.getPrivateKeyAsString().shouldNotBeNullOrEmpty()
            }
        }
        And("I also check if the mobile publickey is not empty") {
            runBlocking {
                localstore.getPublicKeyAsString().shouldNotBeNullOrEmpty()
            }
        }
        Then("I do nothing") {

        }

    }
}