package com.chriscarini.jetbrains.settingssummary.environmentvariables;

import com.chriscarini.jetbrains.messages.Messages;
import com.chriscarini.jetbrains.settingssummary.environmentvariables.settings.SettingsManager;
import com.intellij.openapi.project.Project;
import com.intellij.settingsSummary.ProblemType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * A Settings Summary {@link ProblemType} that provides all of the system environment variables, minus the
 * environment variables that the user has explicitly excluded in the settings.
 */
public class EnvironmentVariablesSettingsSummaryProblemType implements ProblemType {
    @NonNls
    private static final String ENV_VAR_FORMAT = "%s:%s\n";

    /**
     * Get The System Environment Variables
     *
     * @return A {@link Map} of Environment Variables
     */
    public static Map<String, String> getSystemEnvironmentVariables() {
        return System.getenv();
    }

    /**
     * Provide a "friendly name" in the drop-down of the "Settings Summary" dialog.
     *
     * @return The friendly name of this {@link ProblemType}
     */
    @Override
    public String toString() {
        return Messages.get("environment.variables");
    }

    @NotNull
    @Override
    public String collectInfo(@NotNull Project project) {
        final StringBuilder envVarStringBuilder = new StringBuilder();
        final Map<String, String> envVar = getSystemEnvironmentVariables();
        final List<String> envVarToExclude = SettingsManager.getInstance().getExcludeVars();
        for (final String envName : EnvVarUtils.getSortedDistinctList(envVar.keySet())) {
            if (!envVarToExclude.contains(envName.trim())) {
                envVarStringBuilder.append(String.format(ENV_VAR_FORMAT, envName, envVar.get(envName)));
            }
        }
        return envVarStringBuilder.toString();
    }
}
