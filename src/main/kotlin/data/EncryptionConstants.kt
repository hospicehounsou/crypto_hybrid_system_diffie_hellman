package data

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

object EncryptionConstants {
    const val SERVER_PUBLIC_KEY = "mkb_server_public_client"
    const val CIPHER_AES_ALGO_PADDING = "AES/ECB/PKCS7Padding"
    const val ANDROID_RSA_PRIVATE_KEY = "mkb_android_privatekey"
    const val KEY_PAIR_TYPE = "ECDH" //or RSA
    const val CURVE_NAME = "secp384r1"
    const val SIGNATURE_ALGORITHM = "SHA512withECDSA"
    const val ALGORITHM_AES = "AES"
}