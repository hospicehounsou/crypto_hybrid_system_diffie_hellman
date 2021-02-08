package domain

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

interface KeyPairGeneratorGeneratorRepository {
    suspend fun generateAndSaveAndroidPrivateAndPublicKeyAsString()
    suspend fun sign(data: ByteArray): ByteArray
    suspend fun verify(signature: ByteArray,
                       data: ByteArray,
                       publicKeyString: String): Boolean
    // suspend fun generateCertificate(): X509Certificate
}