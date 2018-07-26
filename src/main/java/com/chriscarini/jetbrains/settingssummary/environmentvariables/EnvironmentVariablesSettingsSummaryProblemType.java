package com.chriscarini.jetbrains.settingssummary.environmentvariables;

import com.intellij.openapi.project.Project;
import com.intellij.settingsSummary.ProblemType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * A Settings Summary {@link ProblemType} that provides all of the system environment variables.
 */
public class EnvironmentVariablesSettingsSummaryProblemType implements ProblemType {
    /**
     * Provide a "friendly name" in the drop-down of the "Settings Summary" dialog.
     * @return The friendly name of this {@link ProblemType}
     */
    @Override
    public String toString() {
        return "Environment Variables";
    }

    @NotNull
    @Override
    public String collectInfo(@NotNull Project project) {
        final StringBuilder envVarStringBuilder = new StringBuilder();
        Map<String, String> envVar = System.getenv();
        for (final String envName : envVar.keySet()) {
            envVarStringBuilder.append(String.format("%s:%s\n", envName, envVar.get(envName)));
        }
        return envVarStringBuilder.toString();
    }
}
