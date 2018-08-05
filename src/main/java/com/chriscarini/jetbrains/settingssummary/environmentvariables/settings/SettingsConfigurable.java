package com.chriscarini.jetbrains.settingssummary.environmentvariables.settings;

import com.chriscarini.jetbrains.messages.Messages;
import com.chriscarini.jetbrains.settingssummary.environmentvariables.EnvVarUtils;
import com.chriscarini.jetbrains.settingssummary.environmentvariables.EnvironmentVariablesSettingsSummaryProblemType;
import com.intellij.openapi.options.Configurable;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * A {@link Configurable} that provides the user the ability to be explicit and exclude any environment variables from
 * being included in the Settings Summary.
 */
public class SettingsConfigurable implements Configurable {
    private final JBTextField myTextField = new JBTextField();
    private final Map<String, JBTextField> labelMap = new ConcurrentHashMap<>();

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return Messages.get("environment.variables.settings");
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        // Set the user input field to contain the currently saved settings
        setUserInputFieldFromSavedSettings();

        // Create a simple form to display the all current environment variables
        final Map<String, String> sysEnvVar =
                EnvironmentVariablesSettingsSummaryProblemType.getSystemEnvironmentVariables();
        final FormBuilder envVarForm = FormBuilder.createFormBuilder();
        for (final String envName : EnvVarUtils.getSortedDistinctList(sysEnvVar.keySet())) {
            final JBTextField label = new JBTextField(envName);
            label.setEditable(false);
            label.setBorder(BorderFactory.createEmptyBorder());
            label.addMouseListener(new ClickingMouseListener(label));
            labelMap.put(envName, label);
            envVarForm.addLabeledComponent(label, new JBLabel(sysEnvVar.get(envName)));
        }
        refreshTableLabels();
        final JPanel currentEnvVarPanel = envVarForm.getPanel();

        return FormBuilder.createFormBuilder()
                .addLabeledComponent(Messages.get("exclude.variable.names"), myTextField)
                .addTooltip(Messages.get("exclude.variable.names.tooltip"))
                .addSeparator()
                .addTooltip(Messages.get("exclude.variable.table.tooltip"))
                .addComponent(currentEnvVarPanel)
                .getPanel();

    }

    /**
     * Overridden method; the "Apply" button in the settings dialog is enabled based on the return value of this method.
     * <p>
     * We only want to allow this should the settings be changed from what the {@link SettingsManager} has set.
     *
     * @return {@code false} if the user input list is identical to the saved settings; {@code true} otherwise.
     */
    @Override
    public boolean isModified() {
        final List<String> excludeEnvVarInputist2 = getEnvVarFromUserInputField();
        final List<String> existingSettings = getSavedExcludedEnvVars();
        return !excludeEnvVarInputist2.equals(existingSettings);
    }

    /**
     * Apply the settings; saves the current user input list to the {@link SettingsManager}, and updates the table.
     */
    @Override
    public void apply() {
        // Update the settings to be the input field, split by ",", stripped of whitespace, and non-empty values.
        SettingsManager.getInstance().setExcludedVars(getEnvVarFromUserInputField());
        setUserInputFieldFromSavedSettings();
        refreshTableLabels();
    }

    @NotNull
    private List<String> getEnvVarFromUserInputField() {
        return Arrays.stream(myTextField.getText().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Refresh the ENV VAR labels; for each ENV VAR, mark (pink background) if it's saved as an excluded ENV VAR.
     */
    private void refreshTableLabels() {
        for (Map.Entry<String, JBTextField> entry : labelMap.entrySet()) {
            final String currentEnvVar = entry.getKey();
            final JBTextField currentLabel = labelMap.get(currentEnvVar);

            // The cur ENV_VAR is saved, so mark as 'excluded'
            if (getSavedExcludedEnvVars().contains(currentEnvVar)) {
                currentLabel.setBackground(JBColor.PINK);
            } else {
                currentLabel.setBackground(JBColor.WHITE);
            }
        }
    }

    /**
     * Get the saved settings and update the user input field.
     */
    private void setUserInputFieldFromSavedSettings() {
        updateUserInputField(getSavedExcludedEnvVars());
    }

    /**
     * Update the user input field based on the input value provided by {@code val}
     *
     * @param val The {@link List} of {@link String}s to update the user input field with.
     */
    private void updateUserInputField(@NotNull final List<String> val) {
        myTextField.setText(String.join(",", val));
    }

    @NotNull
    private List<String> getSavedExcludedEnvVars() {
        return SettingsManager.getInstance().getExcludeVars();
    }

    /**
     * A {@link MouseAdapter} that will:
     * (1) update the provided {@link JBTextField}'s background to indicate if the field is being
     * added ({@code JBColor.ORANGE}) or removed ({@code JBColor.GREEN}) to the exclusion list.
     * (2) add the field to the user input list.
     */
    private final class ClickingMouseListener extends MouseAdapter {
        private final JBTextField textField;

        ClickingMouseListener(JBTextField textField) {
            this.textField = textField;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            final String clickedValue = ((JBTextField) e.getComponent()).getText().trim();

            final JBColor color;
            final List<String> userInputField = getEnvVarFromUserInputField();
            if (!userInputField.contains(clickedValue)) {
                userInputField.add(clickedValue);
                color = JBColor.ORANGE;
            } else {
                userInputField.remove(clickedValue);
                color = JBColor.GREEN;
            }
            updateUserInputField(userInputField);
            this.textField.setBackground(color);
        }
    }
}
