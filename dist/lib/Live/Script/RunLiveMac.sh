#!/bin/bash

script_dir=$(dirname "$0")
cd $script_dir
cd ../Tello-live-Nodejs
chmod +x indexMac.js
/usr/local/bin/node indexMac.js
