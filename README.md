# StutterFix — Forge Port for Minecraft 1.20.1

![Mod loader: Forge](https://img.shields.io/badge/modloader-Forge-orange?style=for-the-badge)
![Minecraft: 1.20.1](https://img.shields.io/badge/Minecraft-1.20.1-green?style=for-the-badge)
![GitHub license](https://img.shields.io/github/license/Sekai0NI0itamio/StutterFix263?style=for-the-badge)

**Configures the main threads to reduce stuttering.**

StutterFix lets you control thread priorities so chunk generation and
world ticking stutter less — the render thread and server thread get
dedicated priorities, and the render loop's `yield()` can be removed
for more stable FPS. By default it ships with a sensible configuration.

Unofficial **Forge port for Minecraft 1.20.1** — mechanism only, no GUI:
configuration is edited in `config/stutterfix-config.json`.

### What it does

- Render thread priority (default 10 on >4-core machines, else 5)
- Server thread priority (default 8, else 5)
- Optional remove-`yield()` in the render loop (`renderRemoveYield`)
- Config validated and clamped automatically

### Port changes vs. the original

- In-game options GUI removed — JSON config only (this port's mechanism-only policy)

### Installation

1. Install Forge 47.4.20 for Minecraft 1.20.1
2. Drop `stutterfix-1.20.1-forge-*.jar` into your `mods` folder

### Original mod & credit

This is an unofficial Forge port by **Itamio** of **StutterFix** by
**wisecase2**.

- Original mod: https://modrinth.com/mod/stutterfix
- Original source: https://github.com/wisecase2/StutterFix
- License: MIT (Copyright © 2023 wisecase2) — see `LICENSE`

All credit for the original concept, design, and logic goes to
wisecase2. See `NOTICE`.
