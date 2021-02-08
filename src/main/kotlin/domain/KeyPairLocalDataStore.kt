package domain

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

interface KeyPairLocalDataStore {
    suspend fun getPrivateKeyAsString(): String
    suspend fun savePrivateKeyAsString(privateKey: String): RSAStoreState
    suspend fun getPublicKeyAsString(): String
    suspend fun savePublicKeyAsString(publicKey: String): RSAStoreState
}

sealed class RSAStoreState{
    object Success:RSAStoreState()
    object AlreadyExist:RSAStoreState()
}