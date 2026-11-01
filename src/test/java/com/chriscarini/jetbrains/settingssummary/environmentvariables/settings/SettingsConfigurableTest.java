package com.chriscarini.jetbrains.settingssummary.environmentvariables.settings;

import com.chriscarini.jetbrains.messages.Messages;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableEP;
import com.intellij.openapi.options.ex.ConfigurableWrapper;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.junit.Test;


public class SettingsConfigurableTest extends BasePlatformTestCase {
  @Test
  public void testDisplayNameDoesNotInstantiateConfigurable() {
    final ConfigurableEP<Configurable> extension = ExtensionPointName
        .<ConfigurableEP<Configurable>>create("com.intellij.applicationConfigurable")
        .getExtensionList().stream()
        .filter(ep -> "com.chriscarini.jetbrains.settingssummary.environmentvariables.settings.SettingsConfigurable"
            .equals(ep.instanceClass))
        .findFirst()
        .orElseThrow();

    final ConfigurableWrapper configurable =
        (ConfigurableWrapper) ConfigurableWrapper.wrapConfigurable(extension, true);
    assertNotNull(configurable);
    assertNull(configurable.getRawConfigurable());
    assertEquals(Messages.get("environment.variables.settings"), configurable.getDisplayName());
    assertNull(configurable.getRawConfigurable());
  }
}
