::@echo off
set MAIN_DRIVE=E:
subst F: %MAIN_DRIVE%\Softwares
label F: SOFTWARES

subst G: %MAIN_DRIVE%\Music
label G: MUSIC

subst H: %MAIN_DRIVE%\Film
label H: FILM
pause