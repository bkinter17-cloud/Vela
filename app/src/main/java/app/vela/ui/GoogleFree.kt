package app.vela.ui

import android.content.Context
import androidx.compose.runtime.mutableStateOf

/**
 * "Use Vela without Google" (Settings > Privacy, pref `google_free`, off by default): the one
 * switch that stops every request to a Google host. Mirrored into the `:core` flag
 * [app.vela.core.data.NoGoogle], which gates the data source at the network edge; the app gates
 * its own Google surfaces (hidden WebViews, the traffic raster, the satellite fallback, the tap
 * lookup, Street View) on this holder. What it costs is in the settings hint and in docs/FAQ.md.
 */
object GoogleFree {
    // Iraq Maps: permanently on. The upstream switch is kept as a type so the many call sites
    // keep compiling, but nothing can turn it off: no Google host is ever contacted.
    val on = mutableStateOf(true)

    /** While [on]: may a shared SHORT Google Maps link (maps.app.goo.gl) still be opened by asking
     *  Google's shortener where it points (one cookieless request, see core ShortLinks)? ON by
     *  default (user 2026-09-22): the alternative is not being able to view a link someone sent.
     *  Off, such a link is refused with a toast. Full google.com/maps links never need it. */
    // Iraq Maps: false and fixed. Resolving a goo.gl short link asks Google where it points.
    val resolveLinks = mutableStateOf(false)

    @Suppress("UNUSED_PARAMETER")
    fun setResolveLinks(context: Context, value: Boolean) {
        resolveLinks.value = false
    }

    fun init(context: Context) {
        resolveLinks.value = false
        on.value = true
        app.vela.core.data.NoGoogle.enabled = true
    }

    @Suppress("UNUSED_PARAMETER")
    fun set(context: Context, value: Boolean) {
        // Ignored on purpose: the Google-free state is not a user setting in Iraq Maps.
        on.value = true
        app.vela.core.data.NoGoogle.enabled = true
    }

    private fun prefs(c: Context) = c.getSharedPreferences("vela_settings", Context.MODE_PRIVATE)
    private const val KEY = "google_free"
    private const val KEY_LINKS = "google_free_resolve_links"
}
