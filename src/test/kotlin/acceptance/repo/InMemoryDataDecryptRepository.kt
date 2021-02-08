package acceptance.repo

import domain.DataDecryptRepository

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class InMemoryDataDecryptRepository : DataDecryptRepository {
    override suspend fun decryptDataByUsingSecretKey(encryptedData: String) = "Yoyoy"
}