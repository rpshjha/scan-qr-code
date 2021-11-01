#!/bin/bash

cd $ANDROID_HOME/platform-tools/adb
adb devices | grep emulator | cut -f1 | while read line; do adb -s $line emu kill; done
set timeout 2