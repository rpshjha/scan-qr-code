#!/usr/bin/expect -f
 
set timeout -1
spawn ./run_create_avd_command.sh
set timeout 3
expect {Do you wish to create a custom hardware profile? [no] }
send -- "no\r"
expect eof