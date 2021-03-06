/**
  * Licensed to the Apache Software Foundation (ASF) under one
  * or more contributor license agreements.  See the NOTICE file
  * distributed with this work for additional information
  * regarding copyright ownership.  The ASF licenses this file
  * to you under the Apache License, Version 2.0 (the
  * "License"); you may not use this file except in compliance
  * with the License.  You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
lazy val commonSettings = Seq(
  version := "0.0.1",
  organization := "p2p",
  scalaVersion := "2.13.3",
  scalacOptions := Seq("-deprecation"),
  libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.7.25",
    "org.slf4j" % "slf4j-simple" % "1.7.25",
    "org.scalatest" %% "scalatest" % "3.2.0" % "test"
  )
)

lazy val coreSettings = Seq(
  resolvers ++= Seq(
    "jitpack" at "https://jitpack.io"
  ),
  libraryDependencies ++= Seq(
    "com.github.multiformats" % "java-multiaddr" % "1.4.1",
    "com.google.guava" % "guava" % "30.1.1-jre",
    "com.github.multiformats" % "java-multihash" % "v1.3.0"
  )
)

lazy val core = (project in file("core")).settings(commonSettings, coreSettings)

lazy val all = (project in file(".")).aggregate(core)
