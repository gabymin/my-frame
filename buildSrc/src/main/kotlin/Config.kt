import java.io.File
import java.util.*
object Configs {
    const val applicationId = "com.westig.frame"
    const val versionCode = 100
    const val versionName = "1.0.0"

    const val compileSdk = 30
    const val minSdk = 24
    const val targetSdk = 30
    const val buildTools = "30.0.3"

    const val agpVersion = "4.0.0"

    private val props by lazy {
        val propFile = File("local.properties")
        if (propFile.exists()) {
            val fis = propFile.inputStream()
            val props = Properties()
            props.load(fis)
            props
        } else {
            null
        }
    }
    val keyAlias = props?.getProperty("sign.key")
    val keyPassword = props?.getProperty("sign.keyPass")
    val storeFile = props?.getProperty("sign.keystore")
    val storePassword = props?.getProperty("sign.keystorePass")

    val isRunAlone =  false
}