#!/bin/bash

script_dir=$(dirname "$0")
cd $script_dir
cd ../Tello-live-Nodejs
chmod +x index.js
/usr/local/bin/node /Users/micheacolautti/Documents/GitHub/Drone-2.0/src/Drone2.0/Live/Tello-live-Nodejs/index.js 
