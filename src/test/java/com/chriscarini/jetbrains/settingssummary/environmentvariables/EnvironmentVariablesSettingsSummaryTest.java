package com.chriscarini.jetbrains.settingssummary.environmentvariables;

import com.intellij.openapi.project.Project;
import com.intellij.testFramework.LightPlatformTestCase;
import java.util.Map;
import org.mockito.Mockito;


public class EnvironmentVariablesSettingsSummaryTest extends LightPlatformTestCase {
  @Override
  public void setUp() throws Exception {
    super.setUp();
  }

  public void testCollectInfo() {
    // Mock a {@link Project} to pass into the {@link ProblemType#collectInfo()} call.
    final Project project = Mockito.mock(Project.class);
    // Test
    final String info = new EnvironmentVariablesSettingsSummary().collectInfo(project);

    for (Map.Entry<String, String> env : System.getenv().entrySet()) {
      final String key = env.getKey();
      assert key != null;

      final String value = env.getValue();
      assert value != null;

      assert info.contains(key);
      assert info.contains(value);
      assert info.contains(String.format("%s:%s", key, value));
    }
  }
}