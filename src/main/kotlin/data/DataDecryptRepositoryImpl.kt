package data


import data.EncryptionConstants.CIPHER_AES_ALGO_PADDING
import data.EncryptionConstants.KEY_PAIR_TYPE
import domain.CoroutineDispatchers
import domain.DataDecryptRepository
import domain.KeyPairLocalDataStore
import kotlinx.coroutines.withContext
import org.bouncycastle.crypto.InvalidCipherTextException
import org.bouncycastle.jcajce.provider.util.BadBlockException
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.nio.charset.Charset
import java.security.InvalidKeyException
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.Security
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.*
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

@Suppress("NewApi")
class DataDecryptRepositoryImpl(private val serverPublicKeyAsString: ServerPublicKey,
                                private val coroutineDispatchers: CoroutineDispatchers,
                                private val keyPairLocalDataStore: KeyPairLocalDataStore
) : DataDecryptRepository {

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

    private suspend fun serverPublicKey() = withContext(coroutineDispatchers.io) {
        val publicSpec = X509EncodedKeySpec(Base64.getDecoder().decode(serverPublicKeyAsString.value))
        val keyFactory: KeyFactory = KeyFactory.getInstance(KEY_PAIR_TYPE)
        keyFactory.generatePublic(publicSpec)
    }


    private suspend fun sharedSecretKey() = withContext(coroutineDispatchers.io) {
        val agreement: KeyAgreement = KeyAgreement.getInstance(KEY_PAIR_TYPE, "BC")
        agreement.init(androidPrivateKey())
        agreement.doPhase(serverPublicKey(), true)
        agreement.generateSecret("AES[192]")
    }


    /*Instead of crash throw BadPaddingException i prefer to send a string to be handled*/
    override suspend fun decryptDataByUsingSecretKey(encryptedData: String): String {
        println(" DataDecryptRepositoryImpl#decryptDataByUsingSecretKey = secretKey: ${Base64.getEncoder().encodeToString(sharedSecretKey().encoded)}")

        return withContext(coroutineDispatchers.io) {
            try {
                val cipher = Cipher.getInstance(CIPHER_AES_ALGO_PADDING)
                cipher.init(Cipher.DECRYPT_MODE, sharedSecretKey())
                val original = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
                String(original, Charset.forName("UTF-8"))
            } catch (ex: Exception) {
                ex.printStackTrace()
                when (ex) {
                    is NoSuchAlgorithmException,
                    is InvalidKeyException,
                    is NoSuchPaddingException,
                    is IllegalBlockSizeException,
                    is BadPaddingException,
                    is InvalidCipherTextException,
                    is BadBlockException,
                    is InvalidKeySpecException -> {
                        "can't decrypt data"
                    }
                    else -> throw ex
                }


            }
        }
    }


}