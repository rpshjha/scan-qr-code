#!/bin/bash

deviceName="TestDevice"

echo "launching avd $deviceName"

# Launch the emulator
$WORKSPACE/android/emulator/emulator @$deviceName

echo "avd started"
