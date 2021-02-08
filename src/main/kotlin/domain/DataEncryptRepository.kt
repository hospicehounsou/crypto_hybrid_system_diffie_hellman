package domain

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

interface DataEncryptRepository {
    suspend fun encryptDataByUsingAESSecretKey(dataToEncrypt:String):String
}