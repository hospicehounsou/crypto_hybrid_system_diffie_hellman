package acceptance.steps

import io.cucumber.java8.En
import kotlinx.coroutines.runBlocking
import acceptance.repo.InMemoryDataDecryptRepository
import org.amshove.kluent.shouldBeEqualTo

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class DecryptCipherSteps(dataDecryptRepository: InMemoryDataDecryptRepository) : En {
    private lateinit var plainDataDecrypt: String

    init {
        Given("The mobile already generates Keypair  and the mobile knows the server public key.") {
            // throw PendingException()
        }
        When("I decrypt data cipher {string} by using the diffie hellman shared key. The shared key is combination of mobile private key and the server public key") { encryptedData: String ->
            plainDataDecrypt = runBlocking { dataDecryptRepository.decryptDataByUsingSecretKey(encryptedData) }
        }
        Then("It success with the plain data {string} as output") { plainData: String ->
            plainDataDecrypt.shouldBeEqualTo(plainData)
        }
    }
}