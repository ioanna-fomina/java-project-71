.DEFAULT_GOAL := build-run
clean:
	./gradlew clean

build:
	./gradlew installDist

run-dist:
	./build/install/app/bin/app

check:
	./gradlew checkstyleMain

check-test:
	./gradlew checkstyleTest

test:
	./gradlew test

run:
	./gradlew run

build-run: build run

.PHONY: build
