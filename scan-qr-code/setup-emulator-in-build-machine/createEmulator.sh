#!/bin/bash



# Configuration
androidSdkVersion="29"
deviceName="TestDevice"
deviceProfile="pixel_xl" # See available profiles with: avdmanager list device
includePlayStore=false # See if SDK Version & Play Store are available together with: sdkmanager --list

echo "creating avd with name $deviceName"

# Obtain the AVD image
imageName=""

if ( $includePlayStore ); then
    imageName="system-images;android-$androidSdkVersion;google_apis_playstore;x86"
else
    imageName="system-images;android-$androidSdkVersion;google_apis;x86"
fi

(for run in {1..1000}; do echo y; done) >> yep.txt

cat ./yep.txt |$WORKSPACE/android/cmdline-tools/tools/bin/sdkmanager $imageName >> /dev/null
cat ./yep.txt |$WORKSPACE/android/cmdline-tools/tools/bin/sdkmanager --licenses

rm ./yep.txt

# Create the AVD
$WORKSPACE/android/cmdline-tools/tools/bin/avdmanager create avd \
  --abi google_apis/x86 \
  --device "$deviceProfile" \
  --force \
  --name "$deviceName" \
  --package $imageName

