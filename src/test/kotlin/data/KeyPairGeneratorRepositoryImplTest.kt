package data
import domain.CoroutineDispatchers
import domain.CoroutineDispatchersImpl
import domain.KeyPairLocalDataStore
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

internal class KeyPairGeneratorRepositoryImplTest {

    private lateinit var keyPairLocalDataStore: KeyPairLocalDataStore
    private lateinit var repo: KeyPairGeneratorRepositoryImpl
    private lateinit var coroutineDispatchers: CoroutineDispatchers

    @Before
    fun beforeAll() {
        coroutineDispatchers = CoroutineDispatchersImpl()
        keyPairLocalDataStore = spyk()
        repo = KeyPairGeneratorRepositoryImpl(keyPairLocalDataStore, coroutineDispatchers)
    }


    @Test
    fun `Save Android private and public key if they were not saved in local `() {
        coEvery { keyPairLocalDataStore.getPrivateKeyAsString() } returns ""
        coEvery { keyPairLocalDataStore.getPublicKeyAsString() } returns ""
        runBlocking {
            repo.generateAndSaveAndroidPrivateAndPublicKeyAsString()
        }
    }
    @Test
    fun `Save Android private and public key if they were  saved in local `() {
        coEvery { keyPairLocalDataStore.getPrivateKeyAsString() } returns FakeData.privateKey
        coEvery { keyPairLocalDataStore.getPublicKeyAsString() } returns FakeData.publicKey
        runBlocking {
            repo.generateAndSaveAndroidPrivateAndPublicKeyAsString()
        }
    }

    @Test
    fun `Generate certificate`() {
        runBlocking {
            //repo.generateCertificate()
        }
    }

    // RSAGeneratorRepositoryImpl Private Key: MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCCX5fYSa+xB1XryHhXAHM8cd9c4F++XuNuiwbHq91IQAg==
    // RSAGeneratorRepositoryImpl Public Key: MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEOweA1JCrWZh3inAXcaOf17nKoVcpRvbKMRDVoyG6NcxlmPvljKcIplbb7oRtyXOZlZY0nKfzj8VLdIYrdWwuyA==
}