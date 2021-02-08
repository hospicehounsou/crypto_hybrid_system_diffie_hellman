package data

import data.EncryptionConstants.CURVE_NAME
import data.EncryptionConstants.KEY_PAIR_TYPE
import data.EncryptionConstants.SIGNATURE_ALGORITHM
import domain.CoroutineDispatchers
import domain.KeyPairGeneratorGeneratorRepository
import domain.KeyPairLocalDataStore
import kotlinx.coroutines.withContext
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.*
import java.security.spec.ECGenParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

class KeyPairGeneratorRepositoryImpl(private val keyPairLocalDataStore: KeyPairLocalDataStore,
                                     private val coroutineDispatchers: CoroutineDispatchers
) : KeyPairGeneratorGeneratorRepository {
    init {
        Security.removeProvider("BC")
        Security.addProvider(BouncyCastleProvider())
    }

    private val keyPair by lazy {
        val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("EC", "BC")
        keyPairGenerator.initialize(ECGenParameterSpec(CURVE_NAME), SecureRandom())
        keyPairGenerator.genKeyPair()
    }

    override suspend fun generateAndSaveAndroidPrivateAndPublicKeyAsString() {
        withContext(coroutineDispatchers.io) {
            if (keyPairLocalDataStore.getPrivateKeyAsString().isEmpty() and keyPairLocalDataStore.getPublicKeyAsString().isEmpty()) {
                println("KeyPairGeneratorGeneratorRepositoryImpl Private Key: ${Base64.getEncoder().encodeToString(keyPair.private.encoded)}")
                println("KeyPairGeneratorGeneratorRepositoryImpl Public Key: ${Base64.getEncoder().encodeToString(keyPair.public.encoded)}")
                storePrivateKey()
                storePublicKey()
            } else {
                println("keyPair is already generated and save")
                println("KeyPairGeneratorGeneratorRepositoryImpl Private Key: ${keyPairLocalDataStore.getPrivateKeyAsString()}")
                println("KeyPairGeneratorGeneratorRepositoryImpl Public Key: ${keyPairLocalDataStore.getPublicKeyAsString()}")
            }
        }
    }

    private suspend fun storePublicKey() {
        withContext(coroutineDispatchers.io) {
            keyPairLocalDataStore.savePublicKeyAsString(Base64.getEncoder().encodeToString(keyPair.public.encoded))
        }
    }

    private suspend fun storePrivateKey() {
        withContext(coroutineDispatchers.io) {
            keyPairLocalDataStore.savePrivateKeyAsString(Base64.getEncoder().encodeToString(keyPair.private.encoded))
        }
    }

    override suspend fun sign(data: ByteArray): ByteArray {
        return withContext(coroutineDispatchers.io) {
            val signature = Signature.getInstance(SIGNATURE_ALGORITHM)
            signature.initSign(keyPair.private)
            signature.update(data)
            signature.sign()
        }

    }

    override suspend fun verify(signature: ByteArray, data: ByteArray, publicKeyString: String): Boolean {
        return withContext(coroutineDispatchers.io) {
            val verifySignature = Signature.getInstance(SIGNATURE_ALGORITHM)
            val bytes = Base64.getDecoder().decode(publicKeyString)
            val publicKey =
                KeyFactory.getInstance(KEY_PAIR_TYPE).generatePublic(X509EncodedKeySpec(bytes))
            verifySignature.initVerify(publicKey)
            verifySignature.update(data)
            verifySignature.verify(signature)
        }

    }

}