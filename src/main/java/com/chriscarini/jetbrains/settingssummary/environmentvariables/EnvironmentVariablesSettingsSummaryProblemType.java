package com.chriscarini.jetbrains.settingssummary.environmentvariables;

import com.chriscarini.jetbrains.messages.Messages;
import com.intellij.openapi.project.Project;
import com.intellij.settingsSummary.ProblemType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * A Settings Summary {@link ProblemType} that provides all of the system environment variables.
 */
public class EnvironmentVariablesSettingsSummaryProblemType implements ProblemType {
    @NonNls
    private static final String ENV_VAR_FORMAT = "%s:%s\n";

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
        Map<String, String> envVar = getSystemEnvironmentVariables();
        for (final String envName : envVar.keySet().stream().sorted().collect(Collectors.toList())) {
            envVarStringBuilder.append(String.format(ENV_VAR_FORMAT, envName, envVar.get(envName)));
        }
        return envVarStringBuilder.toString();
    }

    /**
     * Get The System Environment Variables; package-private access for mocking
     *
     * @return A {@link Map} of Environment Variables
     */
    Map<String, String> getSystemEnvironmentVariables() {
        return System.getenv();
    }
}
