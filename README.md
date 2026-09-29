# FastCrystalSpin (Fabric)

Fixes delayed End Crystal visuals by speeding up crystal display/spin animations for better crystal pvp clarity.

![Downloads](https://img.shields.io/modrinth/dt/fastcrystalspin?logo=modrinth&label=downloads)
[![Build](https://github.com/ggunderscoreg/FastCrystalSpin/actions/workflows/build.yml/badge.svg)](https://github.com/ggunderscoreg/FastCrystalSpin/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## Download
[Download on Modrinth](https://modrinth.com/mod/fastcrystalspin)

## Latest Published Version
**FastCrystalSpin v2.0.2** supports:
- Minecraft 26.2.x
- Fabric Loader
- Fabric API required
- Client-side only

The current source build targets Minecraft 26.1.1 and has version 2.0.1 in
`gradle.properties`. The published 2.0.2 release supports 26.2; this source
tree has not yet been updated to build against 26.2. Set `mod_version` when
preparing a new source release. The jar metadata takes its version from there.

## Older Supported Versions
Older releases are still available for previous Minecraft versions:
- **v2.0.1**: Minecraft 26.1.x
- **v1.0.3**: Minecraft 1.21.x and 1.20.1-6
- **v1.0.2** and older: see GitHub Releases or Modrinth version history

## Features
- Speeds up End Crystal spin animation visually
- Configurable multiplier from 1.0x to 50.0x, including fractional speeds
- Client command support for reloading and changing the multiplier

## Commands
- `/crystalspin reload`
- `/crystalspin set <multiplier>`

The setting is saved to `config/fastcrystalspin.cfg` and can also be edited
there. Run `/crystalspin reload` after editing the file.

## Install
1. Install Fabric Loader
2. Install Fabric API
3. Download the version that matches your Minecraft version
4. Put the jar into your `.minecraft/mods` folder

## Notes
- This mod is visual/client-side only
- Safe to use on vanilla servers
- Download builds from Modrinth

## How it works

A client-side Mixin adjusts each End Crystal's animation time after its normal
tick. Each crystal tracks fractional extra ticks separately, so a 1.5x setting
adds one extra animation tick every two game ticks. Client commands update a
persistent Fabric config file. The mod does not change server-side mechanics.

## Build

Use JDK 25 and run `./gradlew build`. The build and animation-speed tests run
on pushes and pull requests in GitHub Actions.
