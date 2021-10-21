#!/bin/bash

user_home=$(bash -c "cd ~$(printf %q $USER) && pwd")
$user_home/Library/Android/sdk/emulator/emulator -avd testAvd -wipe-data -no-snapshot -no-boot-anim