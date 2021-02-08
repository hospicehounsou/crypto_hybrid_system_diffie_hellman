package domain

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

interface DataDecryptRepository {
    suspend fun decryptDataByUsingSecretKey(encryptedData: String): String
}