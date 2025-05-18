# fix-railway

This project is a Minecraft mod for version 1.19.2, built using Forge 43.3.8. The mod introduces new blocks and items, enhancing the gameplay experience. Below is a brief overview of the project's structure and key components:

## Project Structure

- **src/main/java/com/example/fixrailway**
  - **FixRailway.java**: The main class responsible for initializing the mod and registering events and functionalities.
  - **config/Config.java**: Manages mod configuration options, providing functionality to read and write configurations.
  - **init/BlockInit.java**: Responsible for registering blocks in the mod, including their properties and behaviors.
  - **init/ItemInit.java**: Responsible for registering items in the mod, including their properties and behaviors.
  - **init/ModTags.java**: Defines tags used in the mod for easier management and categorization of blocks and items.
  - **util/ModUtils.java**: Contains utility methods for use by other classes.

- **src/main/resources**
  - **META-INF/mods.toml**: Contains metadata for the mod, including its name, version, and other information.
  - **assets/fixrailway**
    - **blockstates**: Directory for block state files.
    - **lang/en_us.json**: Language translations for the mod, defining text used within the mod.
    - **models/block**: Directory for block model files.
    - **models/item**: Directory for item model files.
    - **textures/block**: Directory for block texture files.
    - **textures/item**: Directory for item texture files.
  - **data/fixrailway**
    - **loot_tables/blocks**: Directory for block loot tables.
    - **recipes**: Directory for item recipes.

- **src/test/java**: Directory for test code.

- **build.gradle**: Gradle build script defining project dependencies and build configurations.

- **gradle.properties**: Contains properties configuration for Gradle.

- **settings.gradle**: Defines settings for the Gradle project.

## Getting Started

To set up the mod, clone the repository and import it into your preferred IDE. Ensure you have the necessary dependencies for Minecraft Forge 43.3.8. Follow the instructions in the build.gradle file for building and running the mod.

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.