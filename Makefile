.DEFAULT_GOAL := build-run

build:
	make -C app ./gradlew clean installDist checkstyleMain

run-dist:
	make -C run-dist

run:
	make -C app run

build-run: build run

.PHONY: build