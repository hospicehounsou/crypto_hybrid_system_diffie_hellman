package data

import domain.CoroutineDispatchersImpl
import domain.KeyPairLocalDataStore
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeNullOrEmpty
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

internal class DataDecryptRepositoryImplTest{

    private lateinit var repo: DataDecryptRepositoryImpl
    private lateinit var localStore: KeyPairLocalDataStore

    @Before
    fun beforeAll() {
        localStore = mockk()
        repo = DataDecryptRepositoryImpl(FakeData.publicKey, CoroutineDispatchersImpl(), localStore)
        coEvery { localStore.getPrivateKeyAsString() } returns FakeData.privateKey
    }

    @Test
    fun `Decrypt data with Rigth key`() {

        val decryptedData = runBlocking {
            repo.decryptDataByUsingSecretKey(
                FakeData.encryptedSecretCode)
        }
        println(decryptedData)
        decryptedData.shouldNotBeNullOrEmpty()
        decryptedData.shouldBeEqualTo("Yoyoy")
    }

    @Test
    fun `Decrypt data with Wrong key`() {

        val decryptedData = runBlocking {
            repo.decryptDataByUsingSecretKey(
                "96kDYQa4G9Z+g5AJfhxOAQ==")
        }
        println(decryptedData)
        decryptedData.shouldNotBeNullOrEmpty()

        decryptedData.shouldBeEqualTo("can't decrypt data")
    }


    ///uO2gCJEY2hcrokLkUOQ6IA==
}