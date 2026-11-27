# Winlator DeX

DeX-only build of Winlator for Samsung DeX / desktop mode. Office-first:
absolute mouse, no pointer trap, Vietnamese UI included.

## Requirements

Samsung DeX or an external display (presentation display). The app exits
with a notice on phones.

## Mouse

- No automatic pointer capture on DeX. The cursor moves absolutely.
- Push the mouse to a screen edge to release capture (desktop mode only).
- Floating exit button appears while captured; `Ctrl+Alt+G` releases
  capture and opens the menu; Back and menu actions also release.
- Cursor sensitivity follows the DeX system pointer speed.

## Quick actions toolbar

Top-right gear button expands: keyboard, release mouse, open menu.

## Clipboard

Android-to-Wine sync is on by default on DeX and live (external changes
are pushed immediately). You can turn it off in Settings; off stays off.

## Recommended config (Galaxy S25+ / Snapdragon 8 Elite, Adreno 830)

- Container screen: 1920x1080 on DeX (default for new containers),
  2560x1440 preset available.
- Graphics: Turnip (auto-selected for Adreno) + Gladio.
- DX wrapper: WineD3D by default (best for office apps); switch to DXVK per container for 3D.
- Startup: Normal (all services) for max compatibility.
- Box64 preset: Performance. Env: WINEESYNC=1,
  WINEDLLOVERRIDES=powershell.exe=d (pre-created for new DeX containers).

## Language

Settings > Language > Tiếng Việt. Missing keys fall back to English.
Wine apps get LC_ALL=vi_VN.UTF-8 when Vietnamese is selected.

## Logs for crash reports

Every session writes `Documents/WinlatorDeX/logs.txt`. Java crashes append to
`Documents/WinlatorDeX/crash.txt`. Attach both when reporting a crash.

## Build

Tag `v*` (or manual dispatch) runs `.github/workflows/build-dex-apk.yml`
and attaches the debug APK to the GitHub release.

## Upstream

DeX fixes proposed at brunodev85/winlator-app#110. This repo diverges
DeX-only (no game mode, no logs console, no phone support).
