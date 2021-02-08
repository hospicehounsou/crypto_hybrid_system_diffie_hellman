package acceptance

import acceptance.repo.InMemoryKeyPairExistsLocalDataStore
import acceptance.repo.InMemoryKeyPairNotExistsLocalDataStore

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class Configurations {
    val keyPairExistsLocalDataStore = InMemoryKeyPairExistsLocalDataStore()
    val keyPairNotExistsLocalDataStore = InMemoryKeyPairNotExistsLocalDataStore()

}