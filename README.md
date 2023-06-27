## VBN Line Scraper
This is a small tool to fetch all lines from the website of the Verkehrsverbund Bremen-Niedersachsen (VBN).

## Use
This is intended to be used in conjunction with another project of mine, the [VBN Diversion Tracker](https://github.com/denizk0461/vbn-diversion-tracker) app.

## Purpose
This tool visits the [line overview of the VBN](https://www.vbn.de/service/linienfahrplaene), where all lines run within the VBN are listed, with additional information also provided. This tool fetches all of this, filters it to avoid duplicate data, and then parses everything into a JSON file, which can then be read by the [VBN Diversion Tracker](https://github.com/denizk0461/vbn-diversion-tracker) app.

## Applicability
The tool is written in Kotlin to run on the JVM. However, as this is not a likely runtime environment for a small server to run in, this project merely serves as a proof-of-concept. Most of the concepts applied here would be easily transferrable to a Python project, which could then be executed on, for example, a Raspberry Pi.