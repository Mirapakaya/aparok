package com.aparokio.aparok.presentation.editor.picker

import android.net.Uri
import com.aparokio.aparok.core_models.Id

interface PickerListener {

    fun onStartCopyFileToCacheDir(uri: Uri)

    fun onCancelCopyFileToCacheDir()

    fun onPickedDocImageFromDevice(ctx: Id, path: String)

    fun onProceedWithFilePath(filePath: String?)
}