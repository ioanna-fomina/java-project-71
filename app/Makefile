.DEFAULT_GOAL := build-run

build:
	./gradlew clean installDist checkstyleMain

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

build-run: build run

.PHONY: build
