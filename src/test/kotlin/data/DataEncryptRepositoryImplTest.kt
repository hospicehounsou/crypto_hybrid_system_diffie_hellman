package data

import domain.CoroutineDispatchersImpl
import domain.KeyPairLocalDataStore
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldNotBeNullOrEmpty
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

internal class DataEncryptRepositoryImplTest{

    private lateinit var repo: DataEncryptRepositoryImpl
    private lateinit var keyPairLocalDataStore: KeyPairLocalDataStore
    private lateinit var serverPublicKey: ServerPublicKey

    @Before
    fun beforeAll() {
        keyPairLocalDataStore = mockk()
        serverPublicKey = mockk()

        repo = DataEncryptRepositoryImpl(serverPublicKey, keyPairLocalDataStore, CoroutineDispatchersImpl())

        coEvery { keyPairLocalDataStore.getPrivateKeyAsString() } returns FakeData.privateKey
    }

    @Test
    fun `Encrypt data by using Key pair`() {
        coEvery { serverPublicKey.value } returns FakeData.publicKey
        val encryptedData = runBlocking { repo.encryptDataByUsingAESSecretKey("Yoyoy") }
        println(encryptedData)
        encryptedData.shouldNotBeNullOrEmpty()
    }


    /*
    FLYP6Ci4fjxG1pZl+3K+oA==

     */


}