package com.chriscarini.jetbrains.settingssummary.environmentvariables.settings;

import com.chriscarini.jetbrains.settingssummary.environmentvariables.EnvVarUtils;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.RoamingType;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The {@link SettingsManager} for this plugin; settings will be stored out to and read from {@code envVarSettingsSummary.xml}.
 */
@State(name = "envVarSettingsSummary", storages = @Storage(value = "envVarSettingsSummary.xml", roamingType = RoamingType.PER_OS))
public class SettingsManager implements PersistentStateComponent<SettingsManager.EnvVarSettingsState> {
    private EnvVarSettingsState myState;

    public static SettingsManager getInstance() {
        return ServiceManager.getService(SettingsManager.class);
    }

    @NotNull
    public List<String> getExcludeVars() {
        if (myState == null) {
            myState = new EnvVarSettingsState();
        }
        if (myState.EXCLUDE_VARS == null) {
            myState.EXCLUDE_VARS = Collections.emptyList();
        }
        return myState.EXCLUDE_VARS;
    }

    void setExcludedVars(final List<String> excludedVars) {
        myState.EXCLUDE_VARS = EnvVarUtils.getSortedDistinctList(excludedVars);
    }

    @Nullable
    @Override
    public EnvVarSettingsState getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull EnvVarSettingsState envVarSettingsState) {
        myState = envVarSettingsState;
    }

    static class EnvVarSettingsState {
        @SuppressWarnings("WeakerAccess")
        public List<String> EXCLUDE_VARS;

        @SuppressWarnings("WeakerAccess")
        public EnvVarSettingsState() {
            this.EXCLUDE_VARS = new CopyOnWriteArrayList<>();
        }
    }
}
