/*
 * Copyright (c) 2021 New Vector Ltd
 * Copyright 2021 Qwerty Networks
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

package im.vector.app

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import im.vector.app.features.pin.UnlockedActivity
import java.util.Locale

class WebMyBusines : AppCompatActivity(), View.OnClickListener,UnlockedActivity{ //VectorBaseActivity<ActivityBinding>()

    private var webView: WebView? = null
    private var buttonClosed: Button? = null
    val USER_AGENT = "Cron"
    val Tag = "user_id"

//    private val session: Session by lazy { activeSessionHolder.getSafeActiveSession()!! }
//    lateinit var activeSessionHolder: ActiveSessionHolder

//   override  fun injectWith(injector: Component) {
//        injector.inject(this)
//    }

    @SuppressLint("LogNotTimber", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_my_busines)

        webView = findViewById<View>(R.id.web_my) as WebView?

//        val userId =  activeSessionHolder.getUserId(session)
//        Log.d(Tag, userId)

        webView!!.settings.javaScriptEnabled = true
        val lang = Locale.getDefault().language
        buttonClosed = findViewById<View>(R.id.buttonClosed) as Button?

        webView!!.loadUrl("https://$lang.mybusines.app")
        webView!!.settings.userAgentString = USER_AGENT
        buttonClosed!!.setOnClickListener(this)

        val anim = AnimationUtils.loadAnimation(this, R.anim.alerter_slide_in_from_left)
        buttonClosed!!.startAnimation(anim)

        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            @TargetApi(Build.VERSION_CODES.N)
            override  fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        webView!!.webViewClient = webViewClient
    }

    override fun onClick(v: View?) {
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.alerter_slide_out_to_right)
        buttonClosed!!.startAnimation(anim2)
        finish()
    }

}
