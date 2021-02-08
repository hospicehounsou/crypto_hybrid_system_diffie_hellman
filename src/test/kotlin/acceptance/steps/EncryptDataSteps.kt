package acceptance.steps

import io.cucumber.java8.En
import kotlinx.coroutines.runBlocking
import acceptance.repo.InMemoryDataEncryptRepository
import org.amshove.kluent.`should be equal to`

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class EncryptDataSteps(dataEncryptRepository: InMemoryDataEncryptRepository) : En {
    private lateinit var cipher: String

    init {
        Given("The mobile already generates Keypair  and the mobile knows the server public key") {
            // throw PendingException()
        }
        When("I Encrypt plain data {string} by using the diffie hellman shared key. The shared key is combination of mobile private key and the server public key") { plainData: String ->
            cipher = runBlocking {
                dataEncryptRepository.encryptDataByUsingAESSecretKey(plainData)
            }
        }
        Then("It success with the cipher {string} as output") { encryptedData: String ->
            cipher.`should be equal to`(encryptedData)
        }
    }
}