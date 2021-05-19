package com.higor.restaurantautomation.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.higor.restaurantautomation.utils.contracts.Writable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.FileSystems

@Component
class QrCodeWriter(@Autowired private val writer: QRCodeWriter): Writable {

    override fun write(filePath: String, content: String) {
        val bitMatrix = this.writer.encode(content, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT)
        val pathSave = FileSystems.getDefault().getPath(filePath)
        MatrixToImageWriter.writeToPath(bitMatrix, EXTENSION.replace(".", "").toUpperCase(), pathSave)
    }

    companion object {
        const val QR_CODE_WIDTH = 350
        const val QR_CODE_HEIGHT = 350
        const val PATH = "/tmp/"
        const val EXTENSION = ".png"
    }
}
