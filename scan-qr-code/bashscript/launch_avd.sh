#!/bin/bash

name_of_avd='Pixel'

$ANDROID_HOME/emulator/emulator -avd $name_of_avd -wipe-data -no-snapshot -no-boot-anim -noaudio &
adb wait-for-device shell 'while [[ -z $(getprop sys.boot_completed) ]]; do sleep 1; done; input keyevent 82'
echo "Waiting 30 secs for us to be really booted"
sleep 30
