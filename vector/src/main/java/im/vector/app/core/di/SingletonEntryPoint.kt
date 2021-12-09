/*
 * Copyright (c) 2021 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.core.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import im.vector.app.WebMyBusines
import im.vector.app.core.dialogs.UnrecognizedCertificateDialog
import im.vector.app.core.error.ErrorFormatter
import im.vector.app.features.call.webrtc.WebRtcCallManager
import im.vector.app.features.home.AvatarRenderer
import im.vector.app.features.navigation.Navigator
import im.vector.app.features.pin.PinLocker
import im.vector.app.features.rageshake.BugReporter
import im.vector.app.features.session.SessionListener
import im.vector.app.features.settings.VectorPreferences
import im.vector.app.features.ui.UiStateRepository
import kotlinx.coroutines.CoroutineScope

@InstallIn(SingletonComponent::class)
@EntryPoint
interface SingletonEntryPoint {

    fun sessionListener(): SessionListener

    fun avatarRenderer(): AvatarRenderer

    fun activeSessionHolder(): ActiveSessionHolder

    fun unrecognizedCertificateDialog(): UnrecognizedCertificateDialog

    fun navigator(): Navigator

    fun errorFormatter(): ErrorFormatter

    fun bugReporter(): BugReporter

    fun vectorPreferences(): VectorPreferences

    fun uiStateRepository(): UiStateRepository

    fun pinLocker(): PinLocker

    fun webRtcCallManager(): WebRtcCallManager

    fun appCoroutineScope(): CoroutineScope
}
