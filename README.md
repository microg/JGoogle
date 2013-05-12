JGoogle
=====

An inofficial Google Client library, with focus on Android. Part of μg Project.

Used Libs
---------
This project makes use of the following external projects:
* [Google's micro-protobuf Library](http://code.google.com/p/micro-protobuf/)
	
	> License: [New BSD License](http://opensource.org/licenses/BSD-3-Clause)
	
	> Source: http://micro-protobuf.googlecode.com/svn/trunk/
* [Apache HTTP Components](http://hc.apache.org/) httpcore and httpclient

	>License: [Apache License](http://www.apache.org/licenses/LICENSE-2.0)

	>Binary: http://archive.apache.org/dist/httpcomponents/httpcore/binary/httpcomponents-core-4.2.3-bin.tar.gz and http://archive.apache.org/dist/httpcomponents/httpclient/binary/httpcomponents-client-4.2.3-bin.tar.gz
	
	>Source: http://archive.apache.org/dist/httpcomponents/httpcore/source/httpcomponents-core-4.2.3-src.tar.gz and http://archive.apache.org/dist/httpcomponents/httpclient/source/httpcomponents-client-4.2.3-src.tar.gz
* [iHarder Base64](http://iharder.net/base64)

	>License: Public Domain

	>Source: http://prdownloads.sourceforge.net/iharder/Base64-2.3.7.zip?download

	>File(s): src/com/google/tools/Base64.java

Building
--------
1. Download and build micro-protobuf
2. Build protobuf-files in proto-folder

		mkdir -p gen && protoc --javamicro_out=gen proto/*.proto

3. Download (and build) httpcore and httpclient
4. Compile src and gen folder (remember adding micro-protobuf, httpcore and httpclient to classpath)

License
-------
> Copyright 2012-2013 μg Project Team

> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at

> http://www.apache.org/licenses/LICENSE-2.0

> Unless required by applicable law or agreed to in writing, software 
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
