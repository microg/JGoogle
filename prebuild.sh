#!/bin/sh
protoc --java_out=gen PlayStore.proto
protoc --java_out=gen CheckIn.proto
