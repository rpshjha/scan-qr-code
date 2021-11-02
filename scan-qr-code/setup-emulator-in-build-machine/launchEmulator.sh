#!/bin/bash

echo "Current workspace is $WORKSPACE"
user_home=$(bash -c "cd ~$(printf %q $USER) && pwd")
deviceName="TestDevice"
# Launch the emulator
$WORKSPACE/android/emulator/emulator @$deviceName
