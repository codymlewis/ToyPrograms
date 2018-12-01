#!/bin/sh

g++ -o FrequencyDevice FrequencyDevice.cpp
./FrequencyDevice $(sed 's/\n/ /g' Changes.txt)
