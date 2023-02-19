.DEFAULT_GOAL := build-run

build:
	make -C app build

run-dist:
	make -C run-dist

run:
	make -C app run

build-run: build run

.PHONY: build