#!/bin/sh

g++ -o FrequencyDevice FrequencyDevice.cpp
./FrequencyDevice $(cat Changes.txt)
