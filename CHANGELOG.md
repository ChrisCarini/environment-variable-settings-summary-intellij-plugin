<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# IntelliJ Platform Plugin Template Changelog

## [Unreleased]
### Added

### Changed
- Upgrading IntelliJ to 2022.1

### Deprecated

### Removed

### Fixed

### Security

## [2.5.2] - 2021-12-01
### Changed
- Upgrading IntelliJ to 2021.3

## [2.5.1] - 2021-10-12
### Added
- Restructured file to extract all variables into file.
- Adding ability to publish to different channels based on SemVer pre-release labels.
- Adding [JetBrains Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html)
- Update Dependabot to include Gradle dependencies.
- Adding GitHub build & release workflows.
- Adding JetBrains Qodana (experimental, testing only)

### Changed
- Upgrading Gradle to 6.6
- Upgrading IntelliJ to 2021.2.2
- Upgrading IntelliJ Gradle plugin to 1.2.0

## [2.5.0] - 2021-07-30
### Changed
- Upgrading IntelliJ to 2021.2

## [2.4.0] - 2021-04-10
### Changed
- Upgrading to 2021.1
- Upgrading IntelliJ Gradle plugin to 0.7.2

## [2.3.0] - 2020-12-01
### Changed
- Upgrading to 2020.3
- Upgrading IntelliJ Gradle plugin to 0.6.5
- Upgrading Java 11 - see [the JetBrains Platform blog post announcing the migration](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/)

## [2.2.0] - 2020-07-29
### Changed
- Upgrading to 2020.2
- Upgrading IntelliJ Gradle plugin to 0.4.21

## [2.1.0] - 2020-04-09
### Changed
- Upgrading to 2020.1
- Upgrading IntelliJ Gradle plugin to 0.4.16
- Upgrading Gradle to 6.2

### Added
- GitHub Workflow Action for <a href="https://github.com/marketplace/actions/intellij-platform-plugin-verifier">IntelliJ Platform Plugin Verifier</a>

## [1.1.0] - 2019-11-28
### Changed
- Upgrading to 2019.3.

## [1.0.0] - 2019-07-25
### Changed
- Upgrading to 2019.2.

### Added
- `"zh"` locale under <a href="https://github.com/ChrisCarini/environment-variable-settings-summary-intellij-plugin/issues/3">#3</a> & <a href="https://github.com/ChrisCarini/environment-variable-settings-summary-intellij-plugin/pull/4">#4</a>

## [0.0.5] - 2019-07-24
### Changed
- Upgrading to 2019.1.3.

### Fixed
- Fixing <a href="https://github.com/ChrisCarini/environment-variable-settings-summary-intellij-plugin/issues/2">#2</a> - No generic resource bundle causes stack trace for certain languages.

## [0.0.4] - 2019-04-01
### Added
- Adding an icon for use in 2019.1 release.

### Changed
- Upgrading for 2019.1.
- Migrating to newer extension points.

## [0.0.3] - 2018-11-30
### Changed
- Upgrading for 2018.3.

## [0.0.2] - 2018-08-04
### Added
- Settings for plugin to allow environment variables to be excluded from the summary output.

## [0.0.1] - 2018-07-25
### Added
- Initial release.