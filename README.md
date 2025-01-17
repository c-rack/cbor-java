cbor-java
=========

A Java 7 implementation of [RFC 7049](http://tools.ietf.org/html/rfc7049): Concise Binary Object Representation ([CBOR](http://cbor.io/))


[![Build Status](https://travis-ci.org/c-rack/cbor-java.svg?branch=master)](https://travis-ci.org/c-rack/cbor-java)
[![Coverage Status](https://coveralls.io/repos/c-rack/cbor-java/badge.svg?branch=master&service=github)](https://coveralls.io/github/c-rack/cbor-java?branch=master)
[![Coverity Scan Build Status](https://scan.coverity.com/projects/1218/badge.svg)](https://scan.coverity.com/projects/1218)
[![Code Quality: Java](https://img.shields.io/lgtm/grade/java/g/c-rack/cbor-java.svg)](https://lgtm.com/projects/g/c-rack/cbor-java/context:java)
[![Total Alerts](https://img.shields.io/lgtm/alerts/g/c-rack/cbor-java.svg)](https://lgtm.com/projects/g/c-rack/cbor-java/alerts)

## Features

* Encodes and decodes **all examples** described in RFC 7049
* Provides a **fluent interface builder** for CBOR messages
* Supports semantic tags
* Supports 64-bit integer values
* Passes all [CPD](http://c-rack.github.io/cbor-java/cpd.html), [PMD](http://c-rack.github.io/cbor-java/pmd.html) and [FindBugs](http://c-rack.github.io/cbor-java/findbugs.html) tests

## Documentation

* [Documentation](http://c-rack.github.io/cbor-java/)
* [JavaDoc](http://c-rack.github.io/cbor-java/apidocs/index.html)

## Maven Dependency

Add this to the dependencies section of your pom.xml file:

```xml
<dependency>
    <groupId>co.nstant.in</groupId>
    <artifactId>cbor</artifactId>
    <version>0.9</version>
</dependency>
```

## Usage

### Encoding Examples

```java
ByteArrayOutputStream baos = new ByteArrayOutputStream();
new CborEncoder(baos).encode(new CborBuilder()
    .add("text")                // add string
    .add(1234)                  // add integer
    .add(new byte[] { 0x10 })   // add byte array
    .addArray()                 // add array
        .add(1)
        .add("text")
        .end()
    .build());
byte[] encodedBytes = baos.toByteArray();
```

```java
byte[] encodedBytes = CborEncoder.encodeToBytes(
    new CborBuilder().add("text").add(1234).build());
```

```java
byte[] encodedBytes = new UnsignedInteger(1234).encodeToBytes();
```

### Decoding Example

```java
ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
List<DataItem> dataItems = new CborDecoder(bais).decode();
for(DataItem dataItem : dataItems) {
    // process data item
}
```

### Streaming Decoding Example

```java
ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
new CborDecoder(bais).decode(new DataItemListener() {

    @Override
    public void onDataItem(DataItem dataItem) {
        // process data item
    }

});
```

### GWT Usage

The library can be used in GWT projects.
You just need to add the sources dependency to your project:

```xml
<dependency>
    <groupId>co.nstant.in</groupId>
    <artifactId>cbor</artifactId>
    <version>0.9</version>
    <classifier>sources</classifier>
</dependency>
```

And the following inherits statement to your module file:

```xml
<inherits name="co.nstant.in.cbor"/>
```

### Preserving order of map entries

By default, [maps are encoded in canonical format](https://tools.ietf.org/html/rfc7049#section-3.9).
If you want to preserve the order of map entries, use the encoder in non-canonical mode:

```java
ByteArrayOutputStream baos = new ByteArrayOutputStream();
new CborEncoder(baos).nonCanonical().encode(new CborBuilder()
    .addMap()
        .put(1, "v1")
        .put(0, "v2")
        .end()
    .build());
byte[] encodedBytes = baos.toByteArray();
```

## Contribution Process

This project uses the [C4 process](https://rfc.zeromq.org/spec:42/C4/) for all code changes.

## License

    Copyright 2013-2023 Constantin Rack
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
