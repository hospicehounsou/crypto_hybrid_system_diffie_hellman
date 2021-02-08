package data


import data.EncryptionConstants.CIPHER_AES_ALGO_PADDING
import data.EncryptionConstants.KEY_PAIR_TYPE
import domain.CoroutineDispatchers
import domain.DataEncryptRepository
import domain.KeyPairLocalDataStore
import kotlinx.coroutines.withContext
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.nio.charset.Charset
import java.security.KeyFactory
import java.security.Security
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyAgreement

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

@Suppress("NewApi")
class DataEncryptRepositoryImpl(private val serverPublicKey: String,
                                private val keyPairLocalDataStore: KeyPairLocalDataStore,
                                private val coroutineDispatchers: CoroutineDispatchers
) : DataEncryptRepository {

    init {
        Security.removeProvider("BC")
        Security.addProvider(BouncyCastleProvider())
    }

    private suspend fun androidPrivateKey() = withContext(coroutineDispatchers.io) {
        val keyFactory: KeyFactory = KeyFactory.getInstance(KEY_PAIR_TYPE)

        val privateSpec = PKCS8EncodedKeySpec(Base64.getDecoder()
            .decode(keyPairLocalDataStore.getPrivateKeyAsString()))
        keyFactory.generatePrivate(privateSpec)
    }


    private suspend fun sharedSecretKey() = withContext(coroutineDispatchers.io) {
        val keyFactory: KeyFactory = KeyFactory.getInstance(KEY_PAIR_TYPE)
        val publicSpec = X509EncodedKeySpec(Base64.getDecoder().decode(serverPublicKey))
        val serverKey = keyFactory.generatePublic(publicSpec)

        val agreement: KeyAgreement = KeyAgreement.getInstance(KEY_PAIR_TYPE, "BC")
        agreement.init(androidPrivateKey())
        agreement.doPhase(serverKey, true)
        agreement.generateSecret("AES[192]")
    }


    override suspend fun encryptDataByUsingAESSecretKey(dataToEncrypt: String): String {
        return withContext(coroutineDispatchers.io) {
            println(" DataEncryptRepositoryImpl#encryptDataByUsingAESSecretKey = secretKey: ${Base64.getEncoder().encodeToString(sharedSecretKey().encoded)}")
            val cipher: Cipher = Cipher.getInstance(CIPHER_AES_ALGO_PADDING)
            cipher.init(Cipher.ENCRYPT_MODE, sharedSecretKey())
            Base64.getEncoder().encodeToString(cipher
                .doFinal(
                    dataToEncrypt.toByteArray(
                        Charset.forName("UTF-8"))))
        }
    }
}