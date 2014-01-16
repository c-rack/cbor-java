cbor-java
=========

Java implementation of [RFC 7049](http://tools.ietf.org/html/rfc7049): Concise Binary Object Representation (CBOR)

### Build Status

[![Build Status](https://travis-ci.org/c-rack/cbor-java.png)](https://travis-ci.org/c-rack/cbor-java)

## Features

* Can encode and decode **all examples** described in RFC 7049
* Fluent interface builder for CBOR messages
* Support for semantic tags
* Support for 64-bit integer values
* Passes all [CPD](http://c-rack.github.io/cbor-java/cpd.html), [PMD](http://c-rack.github.io/cbor-java/pmd.html) and [FindBugs](http://c-rack.github.io/cbor-java/findbugs.html) tests

## Requirements

* Java 7

## Documentation

* [Documentation](http://c-rack.github.io/cbor-java/)
* [JavaDoc](http://c-rack.github.io/cbor-java/apidocs/index.html)

## Installation

Add this to the dependencies section of your pom.xml file:

    <dependency>
        <groupId>co.nstant.in</groupId>
        <artifactId>cbor</artifactId>
        <version>0.3</version>
    </dependency>

### License

Copyright 2014 Constantin Rack

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
