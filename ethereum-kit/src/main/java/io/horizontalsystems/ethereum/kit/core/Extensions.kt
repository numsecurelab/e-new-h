package io.horizontalsystems.ethereum.kit.core

import io.horizontalsystems.hdwalletkit.ECKey
import io.horizontalsystems.hdwalletkit.HDWallet
import org.web3j.crypto.Keys

fun ByteArray.toHexString(): String {
    return this.joinToString(separator = "") {
        it.toInt().and(0xff).toString(16).padStart(2, '0')
    }
}

@Throws(NumberFormatException::class)
fun String.hexStringToByteArray(): ByteArray {
    return ByteArray(this.length / 2) {
        this.substring(it * 2, it * 2 + 2).toInt(16).toByte()
    }
}

fun HDWallet.address(): String {
    val accountKey = privateKey(0, HDWallet.Chain.EXTERNAL.ordinal)
    val pubKey = ECKey.pubKeyFromPrivKey(accountKey.privKey, false)

    val addressWithNoHexPrefix = Keys.getAddress(pubKey.slice(1 until pubKey.size).toByteArray()).toHexString()

    return "0x$addressWithNoHexPrefix"
}