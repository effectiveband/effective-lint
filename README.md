# Android Lint Rules

## XmlIdDetector
- Id should show entire information about component (place without prefix + view type), written in **lowerCamelCase**.


<p align="center">
<img width="800" src="https://user-images.githubusercontent.com/22888209/60888144-e9460980-a277-11e9-9c5d-4f5ea65de807.gif">
</p>


## LayoutNameDetector
- All layouts should have prefix that shows place where this layout will be used.
- For example: `MainActivity -> activity_main.xml`

## How to include in two steps:
- Add `effective-lint.jar` to `libs` folder
- Add dependency `lintChecks files('libs/effective-lint.jar')` to `build.gradle`
