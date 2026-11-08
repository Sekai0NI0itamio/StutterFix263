# StutterFix — Modern Port for Minecraft 26.3 (Fabric)

![Mod loader: Fabric](https://img.shields.io/badge/modloader-Fabric-1976d2?style=for-the-badge)
![Minecraft: 26.3](https://img.shields.io/badge/Minecraft-26.3-green?style=for-the-badge)
![GitHub license](https://img.shields.io/github/license/Sekai0NI0itamio/StutterFix263?style=for-the-badge)

**Configures the main threads to reduce stuttering.**

StutterFix lets you control thread priorities so chunk generation and
world ticking stutter less — the render thread and server thread get
dedicated priorities, and the render loop's `yield()` can be removed
for more stable FPS. By default it ships with a sensible configuration.

Now with a **modern port for Minecraft 26.3 (Fabric)** — mechanism
only, no GUI: configuration is edited in
`config/stutterfix-config.json`.

### What it does

- Render thread priority (default 10 on >4-core machines, else 5)
- Server thread priority (default 8, else 5)
- Optional remove-`yield()` in the render loop (`renderRemoveYield`)
- Config validated and clamped automatically

### Port changes vs. the original

- In-game options GUI removed — JSON config only (this port's
  mechanism-only policy)
- Main-worker-executor overwrite dropped: `Util.getMainWorkerExecutor()`
  was removed in 26.3 (replaced by `backgroundExecutor()`)

### Modern port notes (26.3)

- Java 25 · Fabric Loader 0.20.1+ · Fabric API 0.165.0+26.3
- Built and verified in CI via GitHub Actions, including a headless
  runtime test that launches the client, joins a world, and quits cleanly

### Installation

1. Install Fabric Loader for Minecraft 26.3
2. Install Fabric API
3. Drop `stutterfix263-*.jar` into your `mods` folder

### Original mod & credit

This is an unofficial modern port by **Itamio** of **StutterFix** by
**wisecase2** — the original (1.3M downloads on Modrinth, last updated
for 1.21) has not been updated to 26.3.

- Original mod: https://modrinth.com/mod/stutterfix
- Original source: https://github.com/wisecase2/StutterFix
- License: MIT (Copyright © 2023 wisecase2) — see `LICENSE`

All credit for the original concept, design, and logic goes to
wisecase2. This port exists to bring it to Minecraft 26.3. See `NOTICE`.
