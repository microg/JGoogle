#!/bin/sh
protoc --java_out=gen Market.proto
protoc --java_out=gen CheckIn.proto
