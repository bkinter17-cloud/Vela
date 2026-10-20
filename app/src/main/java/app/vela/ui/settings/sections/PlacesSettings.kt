package app.vela.ui.settings.sections

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.vela.R
import app.vela.ui.settings.GroupDivider
import app.vela.ui.settings.SettingsGroup
import app.vela.ui.settings.SettingsScaffold
import app.vela.ui.settings.ToggleRow

/**
 * Places sub-screen (was Place pages; 2026-09-17 settings reshuffle): where the map's places come
 * from, what the map draws for them, and the five place-page content toggles.
 */
private const val SHOW_PLACES_SOURCE = false

@Composable
internal fun PlacesSettingsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    SettingsScaffold(stringResource(R.string.settings_places), onBack) { topRow ->
        Spacer(Modifier.height(4.dp))
        // Iraq Maps: the source picker is hidden (fixed to the open layer, see MapPoiPrefs).
        if (SHOW_PLACES_SOURCE) { PlacesSourceGroup(topRow); Spacer(Modifier.height(8.dp)) }
        PlacesOnMapGroup()
        Spacer(Modifier.height(8.dp))
        SettingsGroup(title = stringResource(R.string.settings_place_pages)) {
        ReviewAndPhotoRows()
        GroupDivider()
        ToggleRow(
            label = stringResource(R.string.settings_hide_adult),
            checked = app.vela.ui.HideAdult.on.value,
            onCheckedChange = { app.vela.ui.HideAdult.set(context, it) },
            hint = stringResource(R.string.settings_hide_adult_hint),
        )
        GroupDivider()
        ToggleRow(
            label = stringResource(R.string.settings_hide_external_links),
            checked = app.vela.ui.HideExternalLinks.on.value,
            onCheckedChange = { app.vela.ui.HideExternalLinks.set(context, it) },
            hint = stringResource(R.string.settings_hide_external_links_hint),
        )
        }
        Spacer(Modifier.height(24.dp))
    }
}
