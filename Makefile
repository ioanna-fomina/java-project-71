.DEFAULT_GOAL := build-run

build:
	make -C app clean build check check-test test

run-dist:
	make -C app run-dist

test:
	make -C app test

run:
	make -C app run

build-run: build run

.PHONY: build