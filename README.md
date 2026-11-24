# Eclipse EMF Parsley

Eclipse EMF Parsley is an Eclipse project that provides a set of reusable UI components based on EMF (e.g., trees, tables, forms, views, and editors), hiding most of the complexity of internal details, that can be used in your EMF-based plugin out of the box.

These parts are highly customizable via a DSL that lets you change many details even more easily, in a very compact form.

The DSL is implemented in Xtext; thus, it comes with rich and powerful IDE tooling.

Building from source requires JDK 25 for the current Eclipse target platform.
The bundles are compiled with Java 21 source and target levels. Run Maven from
the repository root with `./mvnw -f releng/org.eclipse.emf.parsley.parent/pom.xml`.

* https://eclipse.dev/emf-parsley/

[![Java CI with Maven (Linux)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/maven.yml)
[![Java CI with Maven (macOS)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/macos.yml/badge.svg?branch=master)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/macos.yml)
[![Java CI with Maven (Windows)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/windows.yml/badge.svg?branch=master)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/windows.yml)

[![Java CI with Maven (RAP)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/maven-rap.yml/badge.svg?branch=master)](https://github.com/eclipse-emf-parsley/emf-parsley/actions/workflows/maven-rap.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=eclipse-emf-parsley_emf-parsley&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=eclipse-emf-parsley_emf-parsley)
