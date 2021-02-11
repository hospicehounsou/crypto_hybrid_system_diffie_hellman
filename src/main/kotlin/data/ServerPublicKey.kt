package data

/**
 * @author : hospicehounsou
 * @created : 10/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/
data class ServerPublicKey(val value: String) {
    init {
        require(value.isNotBlank()){
            "The  $value should not be empty."
        }
    }
}