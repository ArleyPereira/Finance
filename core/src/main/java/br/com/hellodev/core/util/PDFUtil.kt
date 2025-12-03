package br.com.hellodev.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import java.util.Locale

fun getFileName(context: Context, uri: Uri): String {
    val contentResolver = context.contentResolver

    val projection = arrayOf(OpenableColumns.DISPLAY_NAME)
    contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            return cursor.getString(nameIndex)
        }
    }

    return "arquivo.pdf"
}

fun getFileSize(context: Context, uri: Uri): Long {
    val contentResolver = context.contentResolver

    val projection = arrayOf(OpenableColumns.SIZE)
    contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            return cursor.getLong(sizeIndex)
        }
    }

    return 0L
}

fun pdfIsAllowedSize(context: Context, uri: Uri, allowedSize: Int = 1024): Boolean {
    val contentResolver = context.contentResolver

    val projection = arrayOf(OpenableColumns.SIZE)
    contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            return (cursor.getLong(sizeIndex) / 1024) <= allowedSize
        }
    }

    return false
}

fun formatFileSize(sizeInBytes: Long?): String {
    if (sizeInBytes == null) return "tamanho desconhecido"

    val kilobyte = 1024L
    val megabyte = kilobyte * 1024
    val gigabyte = megabyte * 1024

    return when {
        sizeInBytes >= gigabyte -> String.format(
            Locale.ROOT,
            "%.1f GB",
            sizeInBytes.toFloat() / gigabyte
        )

        sizeInBytes >= megabyte -> String.format(
            Locale.ROOT,
            "%.1f MB",
            sizeInBytes.toFloat() / megabyte
        )

        sizeInBytes >= kilobyte -> String.format(
            Locale.ROOT,
            "%.1f KB",
            sizeInBytes.toFloat() / kilobyte
        )

        else -> "$sizeInBytes bytes"
    }
}

fun openPdf(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            "Nenhum aplicativo encontrado para visualizar PDF",
            Toast.LENGTH_SHORT
        ).show()
    }
}

