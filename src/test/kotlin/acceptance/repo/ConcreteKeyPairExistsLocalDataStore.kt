package acceptance.repo

import domain.KeyPairLocalDataStore
import domain.RSAStoreState

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class InMemoryKeyPairExistsLocalDataStore : KeyPairLocalDataStore {
    override suspend fun getPrivateKeyAsString() = "Toto"

    override suspend fun savePrivateKeyAsString(privateKey: String) = RSAStoreState.AlreadyExist

    override suspend fun getPublicKeyAsString() = "Titi"

    override suspend fun savePublicKeyAsString(publicKey: String) = RSAStoreState.AlreadyExist
}


class InMemoryKeyPairNotExistsLocalDataStore : KeyPairLocalDataStore {
    override suspend fun getPrivateKeyAsString() = ""

    override suspend fun savePrivateKeyAsString(privateKey: String) = RSAStoreState.Success

    override suspend fun getPublicKeyAsString() = ""

    override suspend fun savePublicKeyAsString(publicKey: String) = RSAStoreState.Success
}