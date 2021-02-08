package acceptance.repo

import domain.DataEncryptRepository

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class InMemoryDataEncryptRepository : DataEncryptRepository {
    override suspend fun encryptDataByUsingAESSecretKey(dataToEncrypt: String) = "sQezmSLKaYeak5fKF2xJSA=="
}