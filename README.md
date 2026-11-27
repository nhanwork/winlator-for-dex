<p align="center">
	<img src="logo.png" width="376" height="128" alt="Winlator DeX Logo" />
</p>

# Winlator DeX

DeX-only build of Winlator for Samsung DeX / Android desktop mode.
Office-first: absolute mouse, no pointer trap, Vietnamese UI included.

- Requires Samsung DeX or an external display (exits on phones)
- No game mode, no relative mouse, no logs console, no gamepad/touch-profile UI
- Touch gestures still work; office input is mouse + keyboard first
- Quick-action toolbar, DeX clipboard sync, per-device recommended config
- Vietnamese locale (`Tiếng Việt`) with English fallback

Docs: [English](docs/DEX.md) · [Tiếng Việt](docs/DEX-VI.md)

APK: see [Releases](../../releases) (`v*` tags are built by CI).

## Upstream

Based on [brunodev85/winlator-app](https://github.com/brunodev85/winlator-app).
DeX fixes proposed upstream in
[winlator-app#110](https://github.com/brunodev85/winlator-app/pull/110).

# Credits and Third-party apps

- GLIBC Patches by [Termux Pacman](https://github.com/termux-pacman/glibc-packages)
- Wine ([winehq.org](https://www.winehq.org/))
- Box86/Box64 by [ptitseb](https://github.com/ptitSeb)
- Mesa (Turnip/Zink/VirGL) ([mesa3d.org](https://www.mesa3d.org))
- DXVK ([github.com/doitsujin/dxvk](https://github.com/doitsujin/dxvk))
- VKD3D ([gitlab.winehq.org/wine/vkd3d](https://gitlab.winehq.org/wine/vkd3d))
- CNC DDraw ([github.com/FunkyFr3sh/cnc-ddraw](https://github.com/FunkyFr3sh/cnc-ddraw))

Special thanks to all the developers involved in these projects.<br>
Thank you to all the people who believe in this project.
